package pl.clinic.api.controller.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.clinic.api.dto.PatientCardDTO;
import pl.clinic.domain.User;
import pl.clinic.integration.configuration.AbstractIT;
import pl.clinic.security.rest.JwtUtils;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static io.restassured.RestAssured.given;


public class PatientCardRestControllerTest extends AbstractIT {

    @LocalServerPort
    private int port;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtils jwtUtils;

    private String jwtToken;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        User admin = DomainData.user1();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        jwtToken = jwtUtils.generateJwtToken(authentication);
    }


    @Test
    public void shouldGetPatientCard() {
        int patientId = 4;

        given()
                .header("Authorization", "Bearer " + jwtToken)
                .when()
                .get("/web-clinic/api/patient-card/{patientId}" ,patientId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void shouldNotGetNonExistentPatientCard() {

        given()
                .header("Authorization", "Bearer " + jwtToken)
                .pathParam("patientId", 999) // ID, które na pewno nie istnieje
                .when()
                .get("/web-clinic/api/patient-card/{patientId}")
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldCreatePatientCard() {

        PatientCardDTO patientCardDTO = DTOFixtures.patientCard1();


        given()
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(ContentType.JSON)
                .body(patientCardDTO)
                .when()
                .post("/web-clinic/api/patient-card")
                .then()
                .statusCode(201);


    }


    @Test
    public void shouldDeletePatientCard() {

        given()
                .header("Authorization", "Bearer " + jwtToken)
                .pathParam("patientCardId", 2) // Zmień na istniejące ID karty pacjenta
                .when()
                .delete("/web-clinic/api/patient-card/{patientCardId}")
                .then()
                .statusCode(204);
    }


}

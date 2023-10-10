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
import pl.clinic.api.dto.PatientUserDTO;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.domain.User;
import pl.clinic.integration.configuration.AbstractIT;
import pl.clinic.security.rest.JwtUtils;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class PatientRestControllerIT extends AbstractIT {

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
    public void shouldReturnAllPatients() {
        given()
                .header("Authorization", "Bearer " + jwtToken)
                .when()
                .get("web-clinic/api/patients")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThanOrEqualTo(1)));
    }

    @Test
    public void shouldReturnPatientDetails() {
        int patientId = 1;
        given()
                .header("Authorization", "Bearer " + jwtToken)
                .when()
                .get("web-clinic/api/patients/{patientId}", patientId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("patientId", equalTo(patientId));
    }

    @Test
    public void shouldRegisterNewPatient() {
        PatientUserDTO newPatient = DTOFixtures.newPatient();

        given()
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(ContentType.JSON)
                .body(newPatient)
                .when()
                .post("web-clinic/api/patients")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
    @Test
    public void shouldUpdatePatient() {
        int patientId = 1; // Zmień na istniejący ID pacjenta w bazie danych
        PatientsDTO updatedPatient= DTOFixtures.patient1();
        updatedPatient.setName("firstName");
        updatedPatient.setSurname("LastName");

        given()
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(ContentType.JSON)
                .body(updatedPatient)
                .when()
                .put("web-clinic/api/patients/{patientId}", patientId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void shouldDeletePatient() {
        int patientId = 1;
        given()
                .header("Authorization", "Bearer " + jwtToken)
                .when()
                .delete("web-clinic/api/patients/{patientId}", patientId)
                .then()
                .statusCode(204);
    }
}

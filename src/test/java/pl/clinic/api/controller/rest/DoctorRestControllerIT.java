package pl.clinic.api.controller.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.DoctorUserDTO;
import pl.clinic.business.DoctorsService;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.User;
import pl.clinic.integration.configuration.AbstractIT;
import pl.clinic.security.rest.JwtUtils;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static io.restassured.RestAssured.given;


public class DoctorRestControllerIT extends AbstractIT {

    @LocalServerPort
    private int port;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    DoctorsService doctorsService;



    @Autowired
    private JwtUtils jwtUtils;

    private String jwtToken;

    @BeforeEach
    public void setup() {

        User admin = DomainData.user1();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
         jwtToken = jwtUtils.generateJwtToken(authentication);
    }

    @Test
    public void testGetAllDoctors() {

        given()
                .log()
                .all()
                .baseUri("http://localhost:" + port)
                .basePath("/web-clinic/api/doctors")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    public void testAddDoctor() {

        DoctorUserDTO doctorUserDTO= DTOFixtures.doctorUserDTO();


        given()
                .baseUri("http://localhost:" + port)
                .basePath("/web-clinic/api/doctors/add")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(ContentType.JSON)
                .body(doctorUserDTO)
                .when()
                .post()
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON);

    }

    @Test
    public void testUpdateDoctor() {

        int doctorId = 1;
        DoctorDTO doctorDTO = DTOFixtures.doctor1();
        doctorDTO.setName("Steve");

        given()
                .baseUri("http://localhost:" + port)
                .basePath("/web-clinic/api/doctors/" + doctorId)
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(ContentType.JSON)
                .body(doctorDTO)
                .when()
                .put()
                .then()
                .statusCode(200);


        Doctors doctor = doctorsService.getDoctor(1);

        Assertions.assertEquals(doctor.getName(),"Steve");
    }


}

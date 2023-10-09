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
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.domain.User;
import pl.clinic.integration.configuration.AbstractIT;
import pl.clinic.security.rest.JwtUtils;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static io.restassured.RestAssured.given;


public class AppointmentRestControllerTest extends AbstractIT {

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
    public void testGetAppointmentsForPatient() {
        int patientId = 1;

        given()
            .header("Authorization", "Bearer " + jwtToken)
            .when()
            .get("/web-clinic/api/appointments/patient/{patientId}", patientId)
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON);

    }

    @Test
    public void testGetCurrentAppointment() {
        int officeId = DomainData.officeAvailabilityDoctor2_1().getOffice().getOfficeId();

        String date = DomainData.officeAvailabilityDoctor2_1().getDate().toString();
        String startTime = DomainData.officeAvailabilityDoctor2_1().getStartTime().toString();

        given()
                .header("Authorization", "Bearer " + jwtToken)
                .pathParam("officeId", officeId)
                .queryParam("date", date)
                .queryParam("startTime", startTime)
                .when()
                .get("/web-clinic/api/appointments/date/{officeId}", officeId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }


    @Test
    public void testScheduleAppointment() {
        int patientId = 1;
        OfficeDoctorAvailabilityDTO officeDoctorAvailabilityDTO = DTOFixtures.officeAvailabilityDoctor2_4();

        given()
            .header("Authorization", "Bearer " + jwtToken)
            .contentType(ContentType.JSON)
            .body(officeDoctorAvailabilityDTO)
            .when()
            .post("/web-clinic/api/appointments/schedule/{patientId}", patientId)
            .then()
            .statusCode(200);
    }

    @Test
    public void testUpdateAppointmentStatus() {
        int appointmentId = 1;
        OfficeDoctorAvailabilityDTO officeDoctorAvailabilityDTO = DTOFixtures.officeAvailabilityDoctor2_4();

        given()
            .header("Authorization", "Bearer " + jwtToken)
            .contentType(ContentType.JSON)
            .body(officeDoctorAvailabilityDTO)
            .when()
            .put("/web-clinic/api/appointments/update-status/{appointmentId}", appointmentId)
            .then()
            .statusCode(200);
    }
}

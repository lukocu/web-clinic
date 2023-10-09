package pl.clinic.api.controller.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import pl.clinic.integration.configuration.AbstractIT;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class AuthRestControllerTest extends AbstractIT {

   @LocalServerPort
    private int port;



    @Test
    public void testAuthenticateUser() {

        String username = "patient1";
        String password = "test";


        given()
                .baseUri("http://localhost:" + port)
                .basePath("/web-clinic/api/auth")
                .contentType(ContentType.JSON)
                .body("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}")
                .when()
                .post("/signin")
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void testRegisterUser() {

        String username = "newuser";
        String email = "newuser@example.com";
        String password = "newpassword";
        String role = "PATIENT";

        // Wywołanie metody registerUser i sprawdzenie odpowiedzi
        given()
                .baseUri("http://localhost:" + port)
                .basePath("/web-clinic/api/auth")
                .contentType(ContentType.JSON)
                .body("{\"username\": \"" + username + "\", \"email\": \"" + email + "\", \"role\": [\"" + role + "\"], \"password\": \"" + password + "\"}")
                .when()
                .post("/signup")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("message", equalTo("User registered successfully!"));
    }
}

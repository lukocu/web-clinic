package pl.clinic.integration.configuration;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;

import java.util.Map;

public interface AuthenticationTestSupport {
    String LOGIN ="/api/auth/signin";
    RequestSpecification requestSpecification();
    RequestSpecification requestSpecificationNoAuthorization();

    default ValidatableResponse login(final String username, final String password){
        return requestSpecificationNoAuthorization()
                .params(Map.of("username",username,"password",password))
                .post(LOGIN)
                .then()
                .statusCode(HttpStatus.FOUND.value());
    }
}

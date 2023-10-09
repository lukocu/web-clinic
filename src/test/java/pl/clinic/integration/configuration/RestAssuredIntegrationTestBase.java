package pl.clinic.integration.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;
import pl.clinic.business.UserService;
import pl.clinic.integration.support.ControllerTestSupport;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public abstract class RestAssuredIntegrationTestBase
    extends AbstractIT
        implements ControllerTestSupport, AuthenticationTestSupport {
    @LocalServerPort
    private int serverPort;
    @Value("${server.servlet.context-path}")
    private String basePath;
    @Autowired
    protected ObjectMapper objectMapper;
@Autowired
    UserService userService;
    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }


    @Test
    void contextLoad(){
        Assertions.assertTrue(true, "Context loaded");
    }


    public RequestSpecification requestSpecification() {
        return given()
                .config(getConfig())
                .basePath(basePath)
                .port(serverPort)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }

    private RestAssuredConfig getConfig() {
        return RestAssuredConfig
                .config()
                .objectMapperConfig(new ObjectMapperConfig()
                        .jackson2ObjectMapperFactory((type, s) -> objectMapper));
    }

    @BeforeEach
    void beforeEach(){
        login("patient1","test");
    }






    private String jwtToken;











}
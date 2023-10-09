package pl.clinic.api.controller.rest;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void handleConstraintViolationException_ReturnsBadRequest() {
        // given
        ConstraintViolationException exception = new ConstraintViolationException("Validation failed", null);

        // when
        ResponseEntity<Object> response = globalExceptionHandler.handle(exception);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }




}

package com.dux.security.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class ControllerAdvisor {

    private static final Logger log = LoggerFactory.getLogger(ControllerAdvisor.class);
    private static final String DEFAULT_ERROR_MESSAGE = "Parece que algo no salio como se esperaba, vuelve a intentarlo mas tarde";
    private static final String BAD_CREDENTIALS_MESSAGE = "Credenciales Invalidas";


    private static final String UNEXPECTED_ERROR_LOG = ":: An error occurred while executing the service, message: {} :::,\n stacktrace: {}";
    private static final String INVALID_CREDENTIALS_LOG = ":: Error occurred while validating credentials, message: {} :::,\n stacktrace: {}";


    @ExceptionHandler({BadCredentialsException.class, ExpiredJwtException.class })
    protected ResponseEntity<ErrorResponse> responseTokenException(Exception ex) {
        log.error(INVALID_CREDENTIALS_LOG,ex.getMessage(), ex.getStackTrace());
        return new ResponseEntity<>(ErrorResponse.builder().code(401).message(BAD_CREDENTIALS_MESSAGE).details(ex.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> responseUnexpectedException(Exception ex) {
        log.error(UNEXPECTED_ERROR_LOG,ex.getMessage(), ex.getStackTrace());
        return new ResponseEntity<>(ErrorResponse.builder().code(500).message(DEFAULT_ERROR_MESSAGE).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

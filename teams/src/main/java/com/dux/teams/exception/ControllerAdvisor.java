package com.dux.teams.exception;

import com.dux.teams.dto.ErrorResponse;
import com.dux.teams.service.impl.TeamServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    private static final Logger log = LoggerFactory.getLogger(ControllerAdvisor.class);
    private static final String DEFAULT_ERROR_MESSAGE = "Parece que algo no salio como se esperaba, vuelve a intentarlo mas tarde";

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> responseNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ErrorResponse.builder().code(404).message(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TeamException.class)
    protected ResponseEntity<ErrorResponse> responseNotFoundException(TeamException ex) {
        return new ResponseEntity<>(ErrorResponse.builder().code(400).message(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> responseUnexpectedException(RuntimeException ex) {
        log.error(":: An error occurred while executing the service, message: {} :::,\n stacktrace: {}",ex.getMessage(), ex.getStackTrace());
        return new ResponseEntity<>(ErrorResponse.builder().code(500).message(DEFAULT_ERROR_MESSAGE).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

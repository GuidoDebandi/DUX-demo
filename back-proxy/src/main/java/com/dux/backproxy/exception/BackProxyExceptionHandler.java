package com.dux.backproxy.exception;

import com.dux.backproxy.client.security.exception.AuthException;
import com.dux.backproxy.client.teams.exception.TeamException;
import com.dux.backproxy.exception.errormanager.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BackProxyExceptionHandler {


  private static final Logger LOGGER = LoggerFactory.getLogger(BackProxyExceptionHandler.class);

  private static final String INVALID_CREDENTIALS_LOG = ":: Error occurred while validating credentials, message: {} :::,\n stacktrace: {}";
  private static final String UNEXPECTED_ERROR_LOG = ":: An error occurred while executing the service, message: {} :::,\n stacktrace: {}";

  private static final String DEFAULT_ERROR_MESSAGE = "Parece que algo no salio como se esperaba, vuelve a intentarlo mas tarde";
  private static final String BAD_CREDENTIALS_MESSAGE = "Credenciales Invalidas";


  @ExceptionHandler(BackProxyGenericException.class)
  public ResponseEntity<ErrorResponse> handBackProxyGenericException(BackProxyGenericException ex) {
    LOGGER.error(UNEXPECTED_ERROR_LOG,ex.getMessage(), ex.getStackTrace());
    return new ResponseEntity<>(ErrorResponse.builder().code(500).message(DEFAULT_ERROR_MESSAGE).build(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AuthException.class)
  protected ResponseEntity<ErrorResponse> handAuthException(AuthException ex) {
    LOGGER.error(INVALID_CREDENTIALS_LOG,ex.getMessage(), ex.getStackTrace());
    return new ResponseEntity<>(ErrorResponse.builder().code(401).message(BAD_CREDENTIALS_MESSAGE).build(), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(TeamException.class)
  protected ResponseEntity<ErrorResponse> handAuthException(TeamException ex) {
    LOGGER.error(INVALID_CREDENTIALS_LOG,ex.getMessage(), ex.getStackTrace());
    return new ResponseEntity<>(ErrorResponse.builder().code(ex.getCode()).message(ex.getMessage()).build(), HttpStatus.valueOf(ex.getCode()));
  }

}

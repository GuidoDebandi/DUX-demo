package com.dux.backproxy.client.security.exception;


import com.dux.backproxy.exception.BackProxyGenericException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SecurityExceptionDecoder implements ErrorDecoder {

  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityExceptionDecoder.class);
  private static final String ERROR_MESSAGE = "Credenciales Invalidas";
  private static final String UNEXPECTED_ERROR_MESSAGE = "Ocurrió un error inesperado, intenta de nuevo mas tarde";

  @Override
  public Exception decode(String methodKey, Response response) {
    LOGGER.error("::: Falló el servicio de Security, response: {} :::", response);
    if( response.status() == 500 ){
      return new BackProxyGenericException(UNEXPECTED_ERROR_MESSAGE);
    }
    return new AuthException(ERROR_MESSAGE);
  }
}

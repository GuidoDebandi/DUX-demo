package com.dux.backproxy.service.impl;

import com.dux.backproxy.client.security.SecurityClient;
import com.dux.backproxy.client.security.dto.User;
import com.dux.backproxy.dto.response.BackProxyResponse;
import com.dux.backproxy.exception.BackProxyGenericException;
import com.dux.backproxy.service.SecurityService;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class SecurityServiceImpl implements SecurityService {
  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);


  private static final String AUTH_CLIENT_FAILED_MESSAGE = "Fallo en el servicio de Autenticacion";
  @Autowired
  private SecurityClient securityClient;

  @Override
  public Boolean verifyToken(String token) {
    return securityClient.verify(token).getData();
  }

  @Override
  public String login(User userDto) {
    try {
      BackProxyResponse<String> decodedToken = securityClient.login(userDto);

      LOGGER.info("RESPONSE FOR LOGIN: {}",decodedToken);
      if (!Integer.valueOf(HttpURLConnection.HTTP_OK).equals(decodedToken.getCode())) {
        LOGGER.error("User token Invalid");
        throw new BackProxyGenericException(AUTH_CLIENT_FAILED_MESSAGE,500);
      }

      return decodedToken.getData();
    } catch (RetryableException e) {
      LOGGER.error("::: Falló el servicio de tokenDecoder, excepción -> {} :::", e.getMessage());
      throw new BackProxyGenericException(AUTH_CLIENT_FAILED_MESSAGE,500);
    }
  }

  @Override
  public String register(User userDto) {
    try {
      BackProxyResponse<String> decodedToken = securityClient.register(userDto);

      if (!Integer.valueOf(HttpURLConnection.HTTP_OK).equals(decodedToken.getCode())) {
        LOGGER.error("User token Invalid");
        throw new BackProxyGenericException(AUTH_CLIENT_FAILED_MESSAGE,500);
      }

      return decodedToken.getData();
    } catch (RetryableException e) {
      LOGGER.error("::: Falló el servicio de tokenDecoder, excepción -> {} :::", e.getMessage());
      throw new BackProxyGenericException(AUTH_CLIENT_FAILED_MESSAGE,500);
    }
  }
}

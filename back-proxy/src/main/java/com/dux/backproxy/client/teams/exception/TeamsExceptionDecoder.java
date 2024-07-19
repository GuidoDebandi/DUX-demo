package com.dux.backproxy.client.teams.exception;


import com.dux.backproxy.exception.BackProxyGenericException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class TeamsExceptionDecoder implements ErrorDecoder {

  public static final String TEAM_NOT_FOUND_MESSAGE = "Equipo no encontrado";

  @Override
  public Exception decode(String methodKey, Response response) {

    log.error("::: Falló el servicio de user core. response: {}:::",response);
    String bodyResp = null;
    try {
      bodyResp = new BufferedReader(
          new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8)).lines()
          .collect(Collectors.joining("\n"));
      if(bodyResp.contains(TEAM_NOT_FOUND_MESSAGE)){
        return new TeamException(TEAM_NOT_FOUND_MESSAGE,404);
      }
    } catch (IOException e) {
      return new BackProxyGenericException("Error");
    }

    return new BackProxyGenericException(bodyResp);
  }
}

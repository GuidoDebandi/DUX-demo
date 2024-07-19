package com.dux.backproxy.client.teams.configuration;

import com.dux.backproxy.client.teams.exception.TeamsExceptionDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamsClientConfig {

  @Bean
  Logger.Level feignUserCoreLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  ErrorDecoder feignUserCoreExceptionDecoder() { return new TeamsExceptionDecoder(); }
}

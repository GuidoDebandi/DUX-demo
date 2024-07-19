package com.dux.backproxy.configuration.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("#{'${cors.allowed-origins}'.split(',')}")
  private String[] allowedOrigins;


  @Bean
  public WebMvcConfigurer corsConfigurer() {

    return new WebMvcConfigurer() {

      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(allowedOrigins)
            .allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(),
                HttpMethod.DELETE.name(), HttpMethod.PUT.name())
            .allowedHeaders("*")
            .maxAge(1800L);
      }
    };
  }
}

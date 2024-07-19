package com.dux.backproxy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@OpenAPIDefinition(
    servers = {
        @Server(url = "/", description = "Default Server URL")
    }
)
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = { "com.dux.backproxy"})
public class BackProxyApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackProxyApplication.class, args);
  }

}

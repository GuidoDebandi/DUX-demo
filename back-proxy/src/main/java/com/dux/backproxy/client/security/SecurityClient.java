package com.dux.backproxy.client.security;

import com.dux.backproxy.client.security.configuration.SecurityClientConfig;
import com.dux.backproxy.client.security.dto.User;
import com.dux.backproxy.dto.response.BackProxyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "security", url = "${security.endpoint}", configuration = SecurityClientConfig.class)
public interface SecurityClient {

  @GetMapping(value = "/auth")
  BackProxyResponse<Boolean> verify(@RequestHeader("Authorization") String token);

  @PostMapping(value ="auth/login")
  BackProxyResponse<String> login(@RequestBody User userDto);

  @PostMapping(value = "auth/register")
  BackProxyResponse<String> register(@RequestBody User userDto);

}

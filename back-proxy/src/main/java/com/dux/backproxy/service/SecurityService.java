package com.dux.backproxy.service;

import com.dux.backproxy.client.security.dto.User;

public interface SecurityService {
  Boolean verifyToken(String token);
  String login(User userDto);
  String register(User userDto);
}

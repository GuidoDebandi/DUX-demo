package com.dux.security.service;

import com.dux.security.dto.LoginResponseDto;
import com.dux.security.dto.UserDto;
import com.dux.security.dto.SecurityResponse;

public interface AuthService {

  SecurityResponse<String> login(UserDto user);
  SecurityResponse<String> signup(UserDto input);
}

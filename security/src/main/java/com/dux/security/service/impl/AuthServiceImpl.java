package com.dux.security.service.impl;

import com.dux.security.dto.SecurityResponse;
import com.dux.security.dto.UserDto;
import com.dux.security.model.User;
import com.dux.security.repository.UserRepository;
import com.dux.security.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
  private static final String LOGIN_OK_MESSAGE = "Login exitoso";


  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  public AuthServiceImpl(
      UserRepository userRepository,
      AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder,
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.tokenService = tokenService;
  }


  @Override public SecurityResponse<String> login(UserDto user) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            user.getPassword()
        )
    );

    User verifiedUser = userRepository.findByUsername(user.getUsername())
        .orElseThrow();

    String jwtToken = tokenService.generateToken(verifiedUser);


    return new SecurityResponse<>(200,LOGIN_OK_MESSAGE,jwtToken);
  }

  @Override public SecurityResponse<String> signup(UserDto input) {
    User user = User.builder()
        .username(input.getUsername())
        .password(passwordEncoder.encode(input.getPassword()))
        .build();
    userRepository.save(user);

    return this.login(UserDto.builder().username(input.getUsername()).password(input.getPassword()).build());
  }




}


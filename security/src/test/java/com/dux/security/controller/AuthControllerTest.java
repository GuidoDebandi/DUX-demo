package com.dux.security.controller;

import com.dux.security.dto.SecurityResponse;
import com.dux.security.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

  @Mock AuthService authService;
  @InjectMocks AuthController authController;

  @Test
  void whenDecodeTokenThenOk() {
    SecurityResponse<String> response = new SecurityResponse<>();
    when(this.authService.verifyToken(any())).thenReturn(response);

    assertEquals(response, this.authController.verify(any()));
  }
}

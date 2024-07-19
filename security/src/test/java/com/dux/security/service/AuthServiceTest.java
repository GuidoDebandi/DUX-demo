package com.dux.security.service;

import com.dux.security.dto.SecurityResponse;
import com.dux.security.service.impl.AuthServiceImpl;
import com.dux.security.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  @InjectMocks AuthServiceImpl tokenService;

  @Test
  void whenDecodeTokenThenOk() {
    SecurityResponse<String> response = new SecurityResponse<>();
    response.setData(TestUtils.STATUS_CODE_OK.toString());

    assertEquals(response.getData(), this.tokenService.verifyToken(TestUtils.SUCCESS_TOKEN).getData());
  }

  @Test
  void whenDecodeTokenThenError() {
    SecurityResponse<String> response = new SecurityResponse<>();
    response.setData(TestUtils.STATUS_CODE_INTERNAL_SERVER_ERROR.toString());

    assertEquals(response.getData(), this.tokenService.verifyToken(TestUtils.ERROR_TOKEN).getData());
  }
}

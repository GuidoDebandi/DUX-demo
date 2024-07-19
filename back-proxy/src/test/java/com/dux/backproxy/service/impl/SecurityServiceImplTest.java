package com.dux.backproxy.service.impl;

import com.dux.backproxy.client.security.SecurityClient;
import com.dux.backproxy.dto.response.BackProxyResponse;
import com.dux.backproxy.enums.BackProxyResponseCode;
import com.dux.backproxy.exception.BackProxyGenericException;
import com.dux.backproxy.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityServiceImplTest {

  @Mock SecurityClient securityClient;

  @InjectMocks SecurityServiceImpl tokenDecoderService;
/*
  @Test
  void whenDecodeUuidThenOk() {
    BackProxyResponse<String> response = new BackProxyResponse<>();
    response.setData(TestUtils.TOKEN);

    when(this.securityClient.decodeUuid(TestUtils.TOKEN)).thenReturn(response);

    assertEquals(response.getData(), this.tokenDecoderService.verifyToken(TestUtils.TOKEN));
  }

  @Test
  void whenValidateOtpThenThrowException() {
    when(this.securityClient.decodeUuid(TestUtils.TOKEN)).thenThrow(
        TestUtils.buildRetryableException());

    assertThrows(BackProxyGenericException.class,
        () -> this.tokenDecoderService.verifyToken(TestUtils.TOKEN));
  }

  @Test
  void whenGetUuidThenOk() {
    BackProxyResponse<String> response = new BackProxyResponse<>();
    response.setCode(HttpURLConnection.HTTP_OK);
    response.setData(TestUtils.TOKEN);

    when(this.securityClient.decodeUuid(TestUtils.TOKEN)).thenReturn(response);

    assertEquals(response.getData(), this.tokenDecoderService.getUuid(TestUtils.TOKEN));
  }

  @Test
  void whenGetUuidNoValid() {
    BackProxyResponse<String> response = new BackProxyResponse<>();
    response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    response.setMessage(BackProxyResponseCode.TOKEN_DECODER_FAILED.name());

    when(this.securityClient.decodeUuid(TestUtils.TOKEN)).thenReturn(response);

    assertThrows(BackProxyGenericException.class,
            () -> this.tokenDecoderService.getUuid(TestUtils.TOKEN));
  }

  @Test
  void whenGetUuidThrowException() {
    when(this.securityClient.decodeUuid(TestUtils.TOKEN)).thenThrow(
            TestUtils.buildRetryableException());

    assertThrows(BackProxyGenericException.class,
            () -> this.tokenDecoderService.getUuid(TestUtils.TOKEN));
  }
*/
}

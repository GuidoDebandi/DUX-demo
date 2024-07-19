package com.dux.backproxy.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BackProxyGenericException extends BackProxyException {
  private static final String SYSCODE = "back-proxy";

  public BackProxyGenericException(String message, int errorCode) {
    super(message, errorCode);
  }

  public BackProxyGenericException(String message) {
    super(message);
  }

  public BackProxyGenericException(String iseMsg, HttpStatus internalServerError) {
  }

  @Override
  public String getSysCode() {
    return SYSCODE;
  }
}

package com.dux.backproxy.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BackProxyException extends RuntimeException {

  @Schema(required = true, description = "Código de error del back-proxy")
  private int errorCode;

  @Schema(required = true, description = "Status de error del back-proxy")
  private HttpStatus httpStatus;

  private Object[] parameters;

  protected BackProxyException() {
    super();
  }

  protected BackProxyException(String message, int errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  protected BackProxyException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  protected BackProxyException(String message) {
    super(message);
  }

  public abstract String getSysCode();
}

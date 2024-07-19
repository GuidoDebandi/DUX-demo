package com.dux.backproxy.dto.response;

import com.dux.backproxy.service.SecurityService;
import com.dux.backproxy.service.TeamService;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BackProxyResponse<T> {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(required = true, description = "Código de respuesta del back-proxy")
  private Integer code;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(required = true, description = "Descripción del código de respuesta del back-proxy")
  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  public BackProxyResponse(T data) {
    this.data = data;
  }
}

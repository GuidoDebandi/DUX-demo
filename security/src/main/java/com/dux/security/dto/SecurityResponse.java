package com.dux.security.dto;

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
public class SecurityResponse<T> {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(description = "Código de respuesta del back-proxy")
  private Integer code;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(description = "Descripción del código de respuesta del back-proxy")
  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  public SecurityResponse(T data) {
    this.data = data;
  }
}

//TODO: Mejorar respuesta interna de servicio para integrar message y code
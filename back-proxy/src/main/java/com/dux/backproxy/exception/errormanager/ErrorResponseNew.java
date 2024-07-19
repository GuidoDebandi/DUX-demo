package com.dux.backproxy.exception.errormanager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseNew {
  @Schema(required = true, description = "Status de error del servicio")
  private String status;

  @Schema(required = true, description = "Código de error del servicio")
  private String code;

  @Schema(required = true, description = "Subtipo de error del servicio")
  private String subType;

  @Schema(required = true, description = "Tipo de error del servicio")
  private String type;

  @Schema(required = true, description = "Descripción de error del servicio")
  private String description;

  @Schema(required = true, description = "Mensaje de error del servicio")
  private String message;

  @Schema(required = true, description = "Detalle de error del servicio")
  private String detail;

  @Schema(required = true, description = "Tipo de error del servicio")
  private String errorType;

  @Schema(required = true, description = "Hora de error del servicio")
  private String timestamp;

  @Schema(required = true, description = "path del servicio")
  private String path;

}

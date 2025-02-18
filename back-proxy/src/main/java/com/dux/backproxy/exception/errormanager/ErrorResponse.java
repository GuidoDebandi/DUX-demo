package com.dux.backproxy.exception.errormanager;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ErrorResponse {

  @JsonProperty("mensaje")
  private String message;
  @JsonProperty("codigo")
  private Integer code;
}


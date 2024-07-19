package com.dux.security.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String message;

    private Integer code;

    private String details;
}

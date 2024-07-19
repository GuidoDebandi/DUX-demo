package com.dux.teams.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    @JsonProperty("mensaje")
    private String message;
    @JsonProperty("codigo")
    private Integer code;
}

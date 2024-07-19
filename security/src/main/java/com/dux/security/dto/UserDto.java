package com.dux.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

}

package com.dux.backproxy.client.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

}

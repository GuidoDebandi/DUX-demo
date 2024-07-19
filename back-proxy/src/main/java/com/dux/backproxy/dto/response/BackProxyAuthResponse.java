package com.dux.backproxy.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class BackProxyAuthResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("token")
    private T data;

    public BackProxyAuthResponse(T data) {
        this.data = data;
    }
}

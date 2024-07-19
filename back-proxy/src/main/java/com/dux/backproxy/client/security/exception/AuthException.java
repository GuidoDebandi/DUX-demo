package com.dux.backproxy.client.security.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuthException extends RuntimeException {
    private static final String SYSCODE = "back-proxy";

    public AuthException(String message) {
        super(message);
    }

    public String getSysCode() {
        return SYSCODE;
    }
}

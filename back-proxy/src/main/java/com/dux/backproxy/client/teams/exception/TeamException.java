package com.dux.backproxy.client.teams.exception;

import lombok.Data;

@Data
public class TeamException extends RuntimeException {
    private static final String SYSCODE = "back-proxy";
    private Integer code;

    public TeamException(String message) {
        super(message);
    }
    public TeamException(String message,Integer code) {
        super(message);
        this.code = code;
    }

    public String getSysCode() {
        return SYSCODE;
    }
}

package com.dux.teams.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TeamException extends RuntimeException{

    public TeamException(String message) {
        super(message);
    }


}

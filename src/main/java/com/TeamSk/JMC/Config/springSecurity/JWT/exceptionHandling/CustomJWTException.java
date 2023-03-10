package com.TeamSk.JMC.Config.springSecurity.JWT.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomJWTException extends RuntimeException {
    private ErrorCode errorCode;
}

package com.TeamSk.JMC.Exception;

import org.springframework.http.HttpStatus;

public class RoomRequestParamRequiredException extends RuntimeException {
    public RoomRequestParamRequiredException() {
    }

    public RoomRequestParamRequiredException(String message) {
        super(message);
    }

    public RoomRequestParamRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoomRequestParamRequiredException(Throwable cause) {
        super(cause);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}

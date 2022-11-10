package com.TeamSk.JMC.Exception;

import org.springframework.http.HttpStatus;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException() {
        super();
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

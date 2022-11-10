package com.TeamSk.JMC.Exception;

import org.springframework.http.HttpStatus;

public class RoomMemberBothNotFoundException extends RuntimeException {
    public RoomMemberBothNotFoundException() {
    }

    public RoomMemberBothNotFoundException(String message) {
        super(message);
    }

    public RoomMemberBothNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoomMemberBothNotFoundException(Throwable cause) {
        super(cause);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

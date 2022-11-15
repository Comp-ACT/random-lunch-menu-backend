package com.TeamSk.JMC.Exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistedMemberException extends RuntimeException {
    public AlreadyExistedMemberException() {
    }

    public AlreadyExistedMemberException(String message) {
        super(message);
    }

    public AlreadyExistedMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistedMemberException(Throwable cause) {
        super(cause);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

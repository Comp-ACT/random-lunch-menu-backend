package com.TeamSk.JMC.Exception;


import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException() {
        super();
    }

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberNotFoundException(Throwable cause) {
        super(cause);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

package com.TeamSk.JMC.Exception;

import org.springframework.http.HttpStatus;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException() {
    }

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantNotFoundException(Throwable cause) {
        super(cause);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

}

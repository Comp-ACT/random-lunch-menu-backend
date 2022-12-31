package com.TeamSk.JMC.Exception.handler;

import com.TeamSk.JMC.Exception.ErrorResponse;
import com.TeamSk.JMC.Exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler<T extends RuntimeException> {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> notFoundException(NotFoundException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(
                        new ErrorResponse(
                                e.getHttpStatus().value(),
                                e.getHttpStatus().getReasonPhrase(),
                                e.getMessage()
                        )
                );
    }
}

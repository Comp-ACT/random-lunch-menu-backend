package com.TeamSk.JMC.Exception.handler;

import com.TeamSk.JMC.Exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleRoomNotFoundException(RoomNotFoundException e) {
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

    @ExceptionHandler(MemberNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleMemberNotFoundException(MemberNotFoundException e) {
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

    @ExceptionHandler(RoomMemberBothNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleRoomMemberBothNotFoundException(RoomMemberBothNotFoundException e) {
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

    @ExceptionHandler(AlreadyExistedMemberException.class)
    protected ResponseEntity<ErrorResponse> handleAlreadyExistedMemberException(AlreadyExistedMemberException e) {
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

    @ExceptionHandler(RestaurantNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleRestaurantNotFoundException(RestaurantNotFoundException e) {
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

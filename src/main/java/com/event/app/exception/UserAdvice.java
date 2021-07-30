package com.event.app.exception;

import com.event.app.payload.ErrorPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAdvice {

  @ResponseBody
  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  ResponseEntity<ErrorPayload> unAuthorized(AuthenticationException ex) {
    return new ResponseEntity(
        ErrorPayload.builder().httpStatus(ex.getHttpStatus().toString()).message(ex.getMessage())
            .build(), HttpStatus.UNAUTHORIZED);
  }

  @ResponseBody
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  ResponseEntity<ErrorPayload> unAuthorized(UserNotFoundException ex) {
    return new ResponseEntity(
        ErrorPayload.builder().httpStatus(ex.getHttpStatus().toString()).message(ex.getMessage())
            .build(), HttpStatus.NOT_FOUND);
  }
}

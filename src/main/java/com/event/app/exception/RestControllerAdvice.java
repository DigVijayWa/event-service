package com.event.app.exception;

import com.event.app.payload.ErrorPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class RestControllerAdvice {

  @ResponseBody
  @ExceptionHandler(EventObjectNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ResponseEntity<ErrorPayload> employeeNotFoundHandler(
      EventObjectNotFoundException ex) {
    return new ResponseEntity(
        ErrorPayload.builder().httpStatus(ex.getHttpStatus().toString()).message(ex.getMessage())
            .build(), HttpStatus.NOT_FOUND);
  }

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
  ResponseEntity<ErrorPayload> unAuthorizedUser(UserNotFoundException ex) {
    return new ResponseEntity(
        ErrorPayload.builder().httpStatus(ex.getHttpStatus().toString()).message(ex.getMessage())
            .build(), HttpStatus.UNAUTHORIZED);
  }

  @ResponseBody
  @ExceptionHandler(InvalidPayloadException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<ErrorPayload> invalidPayloadHandler(
      InvalidPayloadException ex) {

    return new ResponseEntity(
        ErrorPayload.builder().httpStatus(ex.getHttpStatus().toString()).message(ex.getMessage())
            .build(), HttpStatus.BAD_REQUEST);
  }
}
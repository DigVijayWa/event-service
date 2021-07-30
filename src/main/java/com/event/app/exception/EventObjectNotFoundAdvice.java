package com.event.app.exception;

import com.event.app.payload.ErrorPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class EventObjectNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(EventObjectNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ResponseEntity<ErrorPayload> employeeNotFoundHandler(
      EventObjectNotFoundException ex) {
    return new ResponseEntity(
        ErrorPayload.builder().message(ex.getMessage()).httpStatus(ex.getHttpStatus().toString()),
        HttpStatus.NOT_FOUND);
  }
}
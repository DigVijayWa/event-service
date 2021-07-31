package com.event.app.exception;

import com.event.app.payload.ErrorPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidPayloadAdvice {

  @ResponseBody
  @ExceptionHandler(InvalidPayloadException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<ErrorPayload> invalidPayloadHandler(
      InvalidPayloadException ex) {

    return new ResponseEntity(
        ErrorPayload.builder().message(ex.getMessage()).httpStatus(ex.getHttpStatus().toString()),
        HttpStatus.NOT_FOUND);
  }
}

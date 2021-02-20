package com.event.app.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class EventObjectNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(EventObjectNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(EventObjectNotFoundException ex) {
    return ex.toString();
  }


  @ResponseBody
  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  String unAuthorized(AuthenticationException ex) { return ex.toString(); };
}
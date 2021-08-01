package com.event.app.exception;

import org.springframework.http.HttpStatus;

public class EventObjectNotFoundException extends RuntimeException {

  private final String message;

  private final HttpStatus httpStatus;


  public EventObjectNotFoundException(Long id) {
    this.message = String.format("Could not find EventObject %s", id);
    this.httpStatus = HttpStatus.NOT_FOUND;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  @Override
  public String toString() {
    return "{" +
        "\"message\":\"" + message + "\"," +
        "\"httpStatus\":\"" + httpStatus + "\"" +
        '}';
  }
}


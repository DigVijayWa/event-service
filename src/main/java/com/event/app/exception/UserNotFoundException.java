package com.event.app.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{
  private final String message;

  private final HttpStatus httpStatus;

  public UserNotFoundException(String username) {
    this.message = String.format("Could not find User %s",username);
    this.httpStatus = HttpStatus.NOT_FOUND;
  }

  @Override
  public String toString() {
    return "{" +
        "\"message\":\"" + message + "\"," +
        "\"httpStatus\":\"" + httpStatus +"\""+
        '}';
  }
}

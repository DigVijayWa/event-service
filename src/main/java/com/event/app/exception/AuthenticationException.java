package com.event.app.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class AuthenticationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final String message;
  private final HttpStatus httpStatus;

  public AuthenticationException(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
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
        "\"httpStatus\":\"" + httpStatus +"\""+
        '}';
  }
}

package com.event.app.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final String message;
  private final HttpStatus httpStatus;

  public AuthenticationException(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }

  public static AuthenticationExceptionBuilder builder() {
    return new AuthenticationExceptionBuilder();
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

  public static class AuthenticationExceptionBuilder {

    private String message;
    private HttpStatus httpStatus;

    AuthenticationExceptionBuilder() {
    }

    public com.event.app.exception.AuthenticationException.AuthenticationExceptionBuilder message(
        String message) {
      this.message = message;
      return this;
    }

    public com.event.app.exception.AuthenticationException.AuthenticationExceptionBuilder httpStatus(
        HttpStatus httpStatus) {
      this.httpStatus = httpStatus;
      return this;
    }

    public com.event.app.exception.AuthenticationException build() {
      return new AuthenticationException(message, httpStatus);
    }

    public String toString() {
      return "AuthenticationException.AuthenticationExceptionBuilder(message=" + this.message
          + ", httpStatus=" + this.httpStatus + ")";
    }
  }
}

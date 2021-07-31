package com.event.app.exception;

import org.springframework.http.HttpStatus;

public class InvalidPayloadException extends RuntimeException{
  private final String message;

  private final HttpStatus httpStatus;


  public InvalidPayloadException(String uuid, String message) {
    String formatMessage = message + "%s";
    this.message = String.format(formatMessage, uuid);
    this.httpStatus = HttpStatus.PARTIAL_CONTENT;
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

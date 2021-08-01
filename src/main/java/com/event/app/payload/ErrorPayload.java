package com.event.app.payload;

public class ErrorPayload {

  String message;
  String httpStatus;

  public ErrorPayload(String message, String httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }

  public static ErrorPayloadBuilder builder() {
    return new ErrorPayloadBuilder();
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(String httpStatus) {
    this.httpStatus = httpStatus;
  }

  public static class ErrorPayloadBuilder {

    private String message;
    private String httpStatus;

    ErrorPayloadBuilder() {
    }

    public ErrorPayload.ErrorPayloadBuilder message(String message) {
      this.message = message;
      return this;
    }

    public ErrorPayload.ErrorPayloadBuilder httpStatus(String httpStatus) {
      this.httpStatus = httpStatus;
      return this;
    }

    public ErrorPayload build() {
      return new ErrorPayload(this.message, this.httpStatus);
    }
  }
}

package com.event.app.payload;

public class ResponseDTO {
  private String accessToken;

  ResponseDTO(String accessToken) {
    this.accessToken = accessToken;
  }

  public static ResponseDTOBuilder builder() {
    return new ResponseDTOBuilder();
  }

  public String getAccessToken() {
    return this.accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public static class ResponseDTOBuilder {

    private String accessToken;

    ResponseDTOBuilder() {
    }

    public ResponseDTO.ResponseDTOBuilder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public ResponseDTO build() {
      return new ResponseDTO(accessToken);
    }

    public String toString() {
      return "ResponseDTO.ResponseDTOBuilder(accessToken=" + this.accessToken + ")";
    }
  }
}

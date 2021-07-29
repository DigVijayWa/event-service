package com.event.app.inbound;

public class UserPayload {
  private String username;
  private String password;

  UserPayload(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public static UserPayloadBuilder builder() {
    return new UserPayloadBuilder();
  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public static class UserPayloadBuilder {

    private String username;
    private String password;

    UserPayloadBuilder() {
    }

    public UserPayload.UserPayloadBuilder username(String username) {
      this.username = username;
      return this;
    }

    public UserPayload.UserPayloadBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UserPayload build() {
      return new UserPayload(username, password);
    }

    public String toString() {
      return "UserPayload.UserPayloadBuilder(username=" + this.username + ", password="
          + this.password + ")";
    }
  }
}

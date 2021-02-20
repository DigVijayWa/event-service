package com.event.app.inbound;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPayload {
  private String username;
  private String password;
}

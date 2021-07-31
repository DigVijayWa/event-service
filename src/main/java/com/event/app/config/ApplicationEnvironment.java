package com.event.app.config;

import org.springframework.core.env.Environment;

public class ApplicationEnvironment {

  public static boolean isSet(Environment environment, String property) {
    String value = environment.getProperty(property);
    return (value != null && !value.equals("false"));
  }
}

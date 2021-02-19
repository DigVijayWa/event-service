package com.event.app.exception;

public class EventObjectNotFoundException extends RuntimeException {

  public EventObjectNotFoundException(Long id) {
    super(String.format("Could not find EventObject %s",id));
  }
}


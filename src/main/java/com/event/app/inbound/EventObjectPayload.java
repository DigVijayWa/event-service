package com.event.app.inbound;

import com.event.app.bean.User;
import java.util.Date;

public class EventObjectPayload {
  private String eventName;
  private String eventDescription;
  private Date eventDate;
  private String design;
  private String sharableLink;
  private String scope;

  EventObjectPayload( String eventName, String eventDescription, Date eventDate,
      String design, String sharableLink, String scope) {
    this.eventName = eventName;
    this.eventDescription = eventDescription;
    this.eventDate = eventDate;
    this.design = design;
    this.sharableLink = sharableLink;
    this.scope = scope;
  }

  public static EventObjectPayloadBuilder builder() {
    return new EventObjectPayloadBuilder();
  }


  public String getEventName() {
    return this.eventName;
  }

  public String getEventDescription() {
    return this.eventDescription;
  }

  public Date getEventDate() {
    return this.eventDate;
  }

  public String getDesign() {
    return this.design;
  }

  public String getSharableLink() {
    return this.sharableLink;
  }

  public String getScope() {
    return this.scope;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public void setEventDescription(String eventDescription) {
    this.eventDescription = eventDescription;
  }

  public void setEventDate(Date eventDate) {
    this.eventDate = eventDate;
  }

  public void setDesign(String design) {
    this.design = design;
  }

  public void setSharableLink(String sharableLink) {
    this.sharableLink = sharableLink;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public static class EventObjectPayloadBuilder {

    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private String design;
    private String sharableLink;
    private String scope;

    EventObjectPayloadBuilder() {
    }


    public EventObjectPayload.EventObjectPayloadBuilder eventName(String eventName) {
      this.eventName = eventName;
      return this;
    }

    public EventObjectPayload.EventObjectPayloadBuilder eventDescription(String eventDescription) {
      this.eventDescription = eventDescription;
      return this;
    }

    public EventObjectPayload.EventObjectPayloadBuilder eventDate(Date eventDate) {
      this.eventDate = eventDate;
      return this;
    }

    public EventObjectPayload.EventObjectPayloadBuilder design(String design) {
      this.design = design;
      return this;
    }

    public EventObjectPayload.EventObjectPayloadBuilder sharableLink(String sharableLink) {
      this.sharableLink = sharableLink;
      return this;
    }

    public EventObjectPayload.EventObjectPayloadBuilder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public EventObjectPayload build() {
      return new EventObjectPayload(eventName, eventDescription, eventDate, design,
          sharableLink,
          scope);
    }

    public String toString() {
      return "EventObjectPayload.EventObjectPayloadBuilder(eventName="
          + this.eventName + ", eventDescription=" + this.eventDescription + ", eventDate="
          + this.eventDate + ", design=" + this.design + ", sharableLink=" + this.sharableLink
          + ", scope=" + this.scope + ")";
    }
  }
}

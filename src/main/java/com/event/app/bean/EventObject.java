package com.event.app.bean;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Builder
public class EventObject {

  private @Id
  @GeneratedValue
  Long id;
  private String eventName;
  private String eventDescription;
  private Date eventDate;
  private String design;
  private String sharableLink;

  public EventObject() {}

  public EventObject(Long id, String eventName, String eventDescription, Date eventDate,
      String design, String sharableLink) {
    this.id = id;
    this.eventName = eventName;
    this.eventDescription = eventDescription;
    this.eventDate = eventDate;
    this.design = design;
    this.sharableLink = sharableLink;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventObject that = (EventObject) o;
    return id.equals(that.id) &&
        Objects.equals(eventName, that.eventName) &&
        Objects.equals(eventDescription, that.eventDescription) &&
        Objects.equals(eventDate, that.eventDate) &&
        Objects.equals(design, that.design) &&
        Objects.equals(sharableLink, that.sharableLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, eventName, eventDescription, eventDate, design, sharableLink);
  }

  @Override
  public String toString() {
    return "EventObject{" +
        "id=" + id +
        ", eventName='" + eventName + '\'' +
        ", eventDescription='" + eventDescription + '\'' +
        ", eventDate=" + eventDate +
        ", design='" + design + '\'' +
        ", sharableLink='" + sharableLink + '\'' +
        '}';
  }
}


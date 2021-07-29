package com.event.app.bean;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class EventObject {

  @Id
  @GeneratedValue
  @Column(name = "event_id")
  private Long id;
  private String eventName;
  private String eventDescription;
  private Date eventDate;
  private String design;
  private String sharableLink;
  private String scope;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

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

  public EventObject(Long id, String eventName, String eventDescription, Date eventDate,
      String design, String sharableLink, User user) {
    this.id = id;
    this.eventName = eventName;
    this.eventDescription = eventDescription;
    this.eventDate = eventDate;
    this.design = design;
    this.sharableLink = sharableLink;
    this.user = user;
  }

  public EventObject(Long id, String eventName, String eventDescription, Date eventDate,
      String design, String sharableLink, String scope, User user) {
    this.id = id;
    this.eventName = eventName;
    this.eventDescription = eventDescription;
    this.eventDate = eventDate;
    this.design = design;
    this.sharableLink = sharableLink;
    this.scope = scope;
    this.user = user;
  }

  public static EventObjectBuilder builder() {
    return new EventObjectBuilder();
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

  public Long getId() {
    return this.id;
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

  public com.event.app.bean.User getUser() {
    return this.user;
  }

  public void setId(Long id) {
    this.id = id;
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

  public void setUser(com.event.app.bean.User user) {
    this.user = user;
  }

  public static class EventObjectBuilder {

    private Long id;
    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private String design;
    private String sharableLink;
    private String scope;
    private com.event.app.bean.User user;

    EventObjectBuilder() {
    }

    public EventObject.EventObjectBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public EventObject.EventObjectBuilder eventName(String eventName) {
      this.eventName = eventName;
      return this;
    }

    public EventObject.EventObjectBuilder eventDescription(String eventDescription) {
      this.eventDescription = eventDescription;
      return this;
    }

    public EventObject.EventObjectBuilder eventDate(Date eventDate) {
      this.eventDate = eventDate;
      return this;
    }

    public EventObject.EventObjectBuilder design(String design) {
      this.design = design;
      return this;
    }

    public EventObject.EventObjectBuilder sharableLink(String sharableLink) {
      this.sharableLink = sharableLink;
      return this;
    }

    public EventObject.EventObjectBuilder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public EventObject.EventObjectBuilder user(com.event.app.bean.User user) {
      this.user = user;
      return this;
    }

    public EventObject build() {
      return new EventObject(id, eventName, eventDescription, eventDate, design, sharableLink,
          scope,
          user);
    }

    public String toString() {
      return "EventObject.EventObjectBuilder(id=" + this.id + ", eventName=" + this.eventName
          + ", eventDescription=" + this.eventDescription + ", eventDate=" + this.eventDate
          + ", design=" + this.design + ", sharableLink=" + this.sharableLink + ", scope="
          + this.scope + ", user=" + this.user + ")";
    }
  }
}


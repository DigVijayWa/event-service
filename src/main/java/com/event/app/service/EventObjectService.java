package com.event.app.service;

import com.event.app.bean.EventObject;
import com.event.app.bean.User;
import com.event.app.inbound.EventObjectPayload;
import org.springframework.stereotype.Service;

@Service
public class EventObjectService {

  public static EventObject mapEventObject(EventObjectPayload eventObjectPayload, User user) {
    return EventObject.builder()
        .design(eventObjectPayload.getDesign())
        .eventDate(eventObjectPayload.getEventDate())
        .eventDescription(eventObjectPayload.getEventDescription())
        .eventName(eventObjectPayload.getEventName())
        .sharableLink(eventObjectPayload.getSharableLink())
        .scope(eventObjectPayload.getScope())
        .user(user).build();
  }

  public static EventObject mapEventObjectExisting(EventObjectPayload eventObjectPayload, EventObject eventObject, User user) {
    eventObject.setDesign(eventObjectPayload.getDesign());
    eventObject.setEventDate(eventObjectPayload.getEventDate());
    eventObject.setEventDescription(eventObjectPayload.getEventDescription());
    eventObject.setEventName(eventObjectPayload.getEventName());
    eventObject.setSharableLink(eventObjectPayload.getSharableLink());
    eventObject.setScope(eventObjectPayload.getScope());
    eventObject.setUser(user);
    return eventObject;
  }
}

package com.event.app.controller;

import com.event.app.bean.EventObject;
import com.event.app.exception.EventObjectNotFoundException;
import com.event.app.repository.EventObjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventObjectController {

  @Autowired
  EventObjectRepository repository;

  @RequestMapping(value = "/eventObjects")
  @ResponseBody
  List<EventObject> all() {
    return repository.findAll();
  }

  @PostMapping(value = "/eventObjects")
  @ResponseBody
  EventObject newEventObject(@RequestBody EventObject newEventObject) {
    return repository.save(newEventObject);
  }

  @RequestMapping(value = "/eventObjects/{id}")
  @ResponseBody
  EventObject one(@PathVariable Long id) {

    EventObject eventObject = repository.findById(id)
        .orElseThrow(() -> new EventObjectNotFoundException(id));

    return eventObject;
  }

  @PutMapping(value = "/eventObjects/{id}")
  @ResponseBody
  EventObject replaceEventObject(@RequestBody EventObject newEventObject, @PathVariable Long id) {

    return repository.findById(id) //
        .map(eventObject -> {
          eventObject.setEventName(newEventObject.getEventName());
          eventObject.setEventDate(newEventObject.getEventDate());
          eventObject.setDesign(newEventObject.getDesign());
          eventObject.setEventDescription(newEventObject.getEventDescription());
          eventObject.setSharableLink(newEventObject.getSharableLink());
          return repository.save(eventObject);
        }) //
        .orElseGet(() -> {
          newEventObject.setId(id);
          return repository.save(newEventObject);
        });
  }

  @DeleteMapping(value = "/eventObjects/{id}")
  @ResponseBody
  void deleteEventObject(@PathVariable Long id) {
    repository.deleteById(id);
  }
}


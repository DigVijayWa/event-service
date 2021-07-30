package com.event.app.controller;

import com.event.app.bean.EventObject;
import com.event.app.bean.User;
import com.event.app.exception.EventObjectNotFoundException;
import com.event.app.exception.UserNotFoundException;
import com.event.app.inbound.EventObjectPayload;
import com.event.app.repository.EventObjectRepository;
import com.event.app.service.EventObjectService;
import com.event.app.service.UserService;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventObjectController {

  @Autowired
  EventObjectRepository repository;

  @Autowired
  UserService userService;

  Logger logger = LogManager.getLogger();


  @RequestMapping(value = "/eventObjects")
  @ResponseBody
  public List<EventObject> all(@RequestHeader(name = "Authorization") String token) {

    logger.debug(String.format("accessToken %s", token));

    return userService.getUserByToken(token)
        .map(user -> repository.findByUser(user))
        .orElseThrow(() -> new UserNotFoundException(token));
  }

  @PostMapping(value = "/eventObjects")
  @ResponseBody
  protected EventObject newEventObject(@RequestBody EventObjectPayload newEventObject,
      @RequestHeader(name = "Authorization") String token) {

    logger.debug(String.format("accessToken %s", token));

    return userService.getUserByToken(token)
        .map(user -> repository.save(EventObjectService.mapEventObject(newEventObject, user)))
        .orElseThrow(() -> new UserNotFoundException(token));
  }

  @RequestMapping(value = "/eventObjects/{id}")
  @ResponseBody
  EventObject one(@PathVariable Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new EventObjectNotFoundException(id));
  }

  @PutMapping(value = "/eventObjects/{id}")
  @ResponseBody
  EventObject replaceEventObject(@RequestBody EventObjectPayload newEventObject,
      @PathVariable Long id, @RequestHeader(name = "Authorization") String token) {

    logger.debug(String.format("accessToken %s", token));

    User user = userService.getUserByToken(token)
        .orElseThrow(() -> new UserNotFoundException(token));
    return repository.findById(id)
        .map(eventObject -> repository
            .save(EventObjectService.mapEventObjectExisting(newEventObject, eventObject, user)))
        .orElseGet(() -> repository.save(EventObjectService.mapEventObject(newEventObject, user)));
  }

  @DeleteMapping(value = "/eventObjects/{id}")
  @ResponseBody
  void deleteEventObject(@PathVariable Long id) {
    repository.deleteById(id);
  }
}

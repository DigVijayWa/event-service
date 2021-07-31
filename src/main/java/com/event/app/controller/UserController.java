package com.event.app.controller;

import com.event.app.bean.Role;
import com.event.app.bean.User;
import com.event.app.exception.InvalidPayloadException;
import com.event.app.inbound.UserPayload;
import com.event.app.payload.ResponseDTO;
import com.event.app.service.UserService;
import java.util.Arrays;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/signin")
  @ResponseBody
  ResponseDTO login(
      @RequestBody UserPayload maybeUserPayload
  ) {

    return maybeUserPayload.getValidPayload().map(userPayload -> ResponseDTO.builder()
        .accessToken(userService.signin(userPayload.getUsername(), userPayload.getPassword()))
        .build())
        .orElseThrow(() -> new InvalidPayloadException(UUID.randomUUID().toString(),
            "Userpayload has some missing fields "));
  }

  @PostMapping("/signup")
  @ResponseBody
  ResponseDTO signup(
      @RequestBody UserPayload maybeUserPayload
  ) {
    return maybeUserPayload.getValidPayload()
        .map(userPayload -> ResponseDTO.builder().accessToken(userService.signup(
            User.builder().roles(Arrays.asList(Role.ROLE_ADMIN)).password(userPayload.getPassword())
                .username(userPayload.getUsername()).build())).build())
        .orElseThrow(() -> new InvalidPayloadException(UUID.randomUUID().toString(),
            "Userpayload has some missing fields "));
  }

  @DeleteMapping("/delete")
  @ResponseBody
  String deleteUser(@RequestBody UserPayload maybeUserPayload) {

    return maybeUserPayload.getValidPayload().map(
        userPayload -> userService.deleteUser(userPayload.getUsername(), userPayload.getPassword()))
        .orElseThrow(() -> new InvalidPayloadException(UUID.randomUUID().toString(),
            "Userpayload has some missing fields "));
  }
}

package com.event.app.controller;

import com.event.app.bean.Role;
import com.event.app.bean.User;
import com.event.app.inbound.UserPayload;
import com.event.app.payload.ResponseDTO;
import com.event.app.service.UserService;
import java.util.Arrays;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
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
      @RequestBody UserPayload userPayload
  ) {
    return ResponseDTO.builder()
        .accessToken(userService.signin(userPayload.getUsername(), userPayload.getPassword()))
        .build();
  }

  @PostMapping("/signup")
  @ResponseBody
  ResponseDTO signup(
      @RequestBody UserPayload userPayload
  ) {
    return ResponseDTO.builder().accessToken(userService.signup(
        User.builder().roles(Arrays.asList(Role.ROLE_ADMIN)).password(userPayload.getPassword())
            .username(userPayload.getUsername()).build())).build();
  }

  @DeleteMapping("/delete")
  @ResponseBody
  String deleteUser(@RequestBody UserPayload userPayload) {
    return userService.deleteUser(userPayload.getUsername(), userPayload.getPassword());
  }
}

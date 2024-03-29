package com.event.app.service;

import com.event.app.bean.User;
import com.event.app.exception.AuthenticationException;
import com.event.app.repository.UserRepository;
import com.event.app.security.JwtTokenProvider;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public String signin(String username, String password) {
    try {
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider
          .createToken(username, userRepository.findByUsername(username).getRoles());
    } catch (org.springframework.security.core.AuthenticationException e) {
      throw new AuthenticationException("Invalid username/password supplied",
          HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(User user) {
    if (!userRepository.existsByUsername(user.getUsername())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
      return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    } else {
      throw new AuthenticationException("Username is already in use",
          HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String deleteUser(String username, String password) {
    try {
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(username, password));

      User user = findUser(username).orElseThrow(
          () -> new AuthenticationException("Invalid username/password provided",
              HttpStatus.NOT_FOUND));
      userRepository.delete(user);

      return "Success";
    } catch (org.springframework.security.core.AuthenticationException e) {
      throw new AuthenticationException("Invalid username/password supplied",
          HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public Optional<User> getUserByToken(String token) {
    String[] tokens = token.split(" ");
    return findUser(jwtTokenProvider.getUsername(tokens[1]));
  }

  public Optional<User> findUser(String username) {
    if (userRepository.existsByUsername(username)) {
      return Optional.of(userRepository.findByUsername(username));
    }
    return Optional.empty();
  }
}

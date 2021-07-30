package com.event.app.bean;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "user_sys")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", unique = true, nullable = false)
  private Integer id;

  @Column(unique = true, nullable = false)
  private String username;

  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  List<Role> roles;

  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  private Set<EventObject> eventObjects;

  public User() {
  }

  public User(Integer id, String username, String password,
      List<Role> roles, Set<EventObject> eventObjects) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.roles = roles;
    this.eventObjects = eventObjects;
  }

  public User(Integer id, String username, String password,
      List<Role> roles) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.roles = roles;
  }

  public static UserBuilder builder() {
    return new UserBuilder();
  }

  public Integer getId() {
    return this.id;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public List<com.event.app.bean.Role> getRoles() {
    return this.roles;
  }

  public Set<EventObject> getEventObjects() {
    return this.eventObjects;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRoles(List<com.event.app.bean.Role> roles) {
    this.roles = roles;
  }

  public void setEventObjects(Set<EventObject> eventObjects) {
    this.eventObjects = eventObjects;
  }

  public static class UserBuilder {

    private Integer id;
    private String username;
    private String password;
    private List<com.event.app.bean.Role> roles;
    private Set<EventObject> eventObjects;

    UserBuilder() {
    }

    public User.UserBuilder id(Integer id) {
      this.id = id;
      return this;
    }

    public User.UserBuilder username(String username) {
      this.username = username;
      return this;
    }

    public User.UserBuilder password(String password) {
      this.password = password;
      return this;
    }

    public User.UserBuilder roles(List<com.event.app.bean.Role> roles) {
      this.roles = roles;
      return this;
    }

    public User.UserBuilder eventObjects(Set<EventObject> eventObjects) {
      this.eventObjects = eventObjects;
      return this;
    }

    public User build() {
      return new User(id, username, password, roles, eventObjects);
    }

    public String toString() {
      return "User.UserBuilder(id=" + this.id + ", username=" + this.username + ", password="
          + this.password + ", roles=" + this.roles + ", eventObjects=" + this.eventObjects + ")";
    }
  }
}

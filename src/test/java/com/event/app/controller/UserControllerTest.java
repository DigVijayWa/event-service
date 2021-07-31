package com.event.app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.event.app.config.DataSourceConfig;
import javax.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.junit.jupiter.api.BeforeEach;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties
@TestPropertySource(locations = "classpath:application-cloud.properties")
@ContextConfiguration(classes = { DataSourceConfig.class })
@WebAppConfiguration
class UserControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  Logger logger = LogManager.getLogger();

  private static final String VALID_PAYLOAD = "{\"username\":\"frodo\",\"password\":\"testpasswd\"}";

  private static final String INVALID_PAYLOAD = "{\"usernames\":\"frodo\",\"password\":\"testpasswd\"}";

  private MockMvc mockMvc;
  @BeforeEach
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  public void validateServerContext() {
    ServletContext servletContext = webApplicationContext.getServletContext();

    Assertions.assertNotNull(servletContext);
    Assertions.assertTrue(servletContext instanceof MockServletContext);
  }

  @Test
  public void postSignupWithValidContentShouldSuccess()  throws Exception {
    final MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/users/signup")
          .contentType("application/json")
          .content(VALID_PAYLOAD.getBytes()))
        .andDo(print()).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andReturn();

    Assertions.assertEquals("application/json",mvcResult.getResponse().getContentType());
  }

  @Test
  public void postSignupWithInvalidContentShouldFail()  throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.post("/users/signup")
        .contentType("application/json")
        .content(INVALID_PAYLOAD.getBytes()))
        .andDo(print()).andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andReturn();
  }
}
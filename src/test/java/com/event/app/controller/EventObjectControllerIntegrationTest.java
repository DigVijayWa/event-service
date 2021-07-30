package com.event.app.controller;

import com.event.app.config.DataSourceConfig;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties
@TestPropertySource(locations = "classpath:application-cloud.properties")
@ContextConfiguration(classes = { DataSourceConfig.class })
@WebAppConfiguration
class EventObjectControllerIntegrationTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  Logger logger = LogManager.getLogger();

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
  public void getEventObjectsWithoutAuthShoudlThrow400()  throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/eventObjects"))
        .andDo(print()).andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andReturn();
  }
}
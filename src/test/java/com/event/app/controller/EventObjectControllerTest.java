package com.event.app.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.event.app.config.DataSourceConfig;
import com.google.gson.Gson;
import java.util.Map;
import java.util.UUID;
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

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties
@TestPropertySource(locations = "classpath:application-test.properties")
@ContextConfiguration(classes = {DataSourceConfig.class})
@WebAppConfiguration
class EventObjectControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private static String username = UUID.randomUUID().toString();

  private static final String VALID_PAYLOAD =
      "{\"username\":\"" + username + "\",\"password\":\"testpasswd\"}";

  private static final String VALID_PAYLOAD_EVENT_OBJECT = "{\"eventName\":\"some2\",\"eventDescription\":\"details\",\"eventDate\":\"2012-04-23T18:25:43.511Z\",\"design\":\"something\",\"sharableLink\":\"sharableLink\",\"scope\":\"PUBLIC\",\"user_id\":\"1\"}";

  private static final String INVALID_PAYLOAD_EVENT_OBJECT = "{\"eventsName\":\"some2\",\"eventDescription\":\"details\",\"eventDate\":\"2012-04-23T18:25:43.511Z\",\"design\":\"something\",\"sharableLink\":\"sharableLink\",\"scope\":\"PUBLIC\",\"user_id\":\"1\"}";


  Logger logger = LogManager.getLogger();

  private MockMvc mockMvc;

  private MvcResult userSignupResult = null;

  private static boolean isSignupDone = false;

  @BeforeEach
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    if (this.userSignupResult == null) {
      this.userSignupResult = getUserSigninResult();
    }
  }

  @Test
  void validateServerContext() {
    ServletContext servletContext = webApplicationContext.getServletContext();

    Assertions.assertNotNull(servletContext);
    Assertions.assertTrue(servletContext instanceof MockServletContext);
  }

  @Test
  void getEventObjectsWithoutAuthShoudlThrow400() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/eventObjects"))
        .andDo(print()).andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andReturn();
  }

  @Test
  void getEventObjectsWithAuthShoudlBeOk() throws Exception {

    Gson gson = new Gson();
    Map map = gson.fromJson(this.userSignupResult.getResponse().getContentAsString(), Map.class);

    logger.info(map.get("accessToken"));

    this.mockMvc.perform(MockMvcRequestBuilders.get("/eventObjects")
        .header("Authorization", "Bearer " + map.get("accessToken")))
        .andDo(print()).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andReturn();
  }

  @Test
  void postEventObjectsWithAuthShoudlBeOk() throws Exception {

    Gson gson = new Gson();
    Map map = gson.fromJson(this.userSignupResult.getResponse().getContentAsString(), Map.class);

    logger.info(map.get("accessToken"));

    this.mockMvc.perform(MockMvcRequestBuilders.post("/eventObjects")
        .header("Authorization", "Bearer " + map.get("accessToken"))
        .contentType("application/json")
        .content(VALID_PAYLOAD_EVENT_OBJECT.getBytes()))
        .andDo(print()).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andReturn();
  }

  @Test
  void postEventObjectsWithInvalidPayloadAuthShoudlThrow400() throws Exception {

    Gson gson = new Gson();
    Map map = gson.fromJson(this.userSignupResult.getResponse().getContentAsString(), Map.class);

    logger.info(map.get("accessToken"));

    this.mockMvc.perform(MockMvcRequestBuilders.post("/eventObjects")
        .header("Authorization", "Bearer " + map.get("accessToken"))
        .contentType("application/json")
        .content(INVALID_PAYLOAD_EVENT_OBJECT.getBytes()))
        .andDo(print()).andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andReturn();
  }

  MvcResult getUserSigninResult() throws Exception {
    MvcResult tempResult =
        isSignupDone ? this.mockMvc.perform(MockMvcRequestBuilders.post("/users/signin")
            .contentType("application/json")
            .content(VALID_PAYLOAD.getBytes()))
            .andDo(print()).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andReturn() : this.mockMvc.perform(MockMvcRequestBuilders.post("/users/signup")
            .contentType("application/json")
            .content(VALID_PAYLOAD.getBytes()))
            .andDo(print()).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andReturn();

    isSignupDone = true;

    return tempResult;
  }
}

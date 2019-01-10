package com.cybr406.basics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BasicsApplicationTests {

  @Autowired
  MockMvc mockMvc;
  
  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void testHelloWorld() throws Exception {
    String response = mockMvc.perform(get("/helloworld"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    assertEquals("Hello, world.", response);
  }
  
  @Test
  public void testGreeting() throws Exception {
    // Verify the name parameter is required.
    // A 400 response code is expected if its missing.
    mockMvc.perform(get("/greeting"))
        .andExpect(status().is4xxClientError());

    String response = mockMvc.perform(get("/greeting?name=Test"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();
    
    assertEquals("Hello, Test.", response);
  }
  
  @Test
  public void testPathVariable() throws Exception {
    // If no value is provided where the path variable is expected, a 404 not found is expected.
    mockMvc.perform(get("/path/to/"))
        .andExpect(status().isNotFound());

    String response = mockMvc.perform(get("/path/to/victory"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    assertEquals("You're on the path to victory!", response);
  }
  
  @Test
  public void testMap() throws Exception {
    String response = mockMvc.perform(get("/map?key1=aaa&key2=bbb"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    // Verify that all request parameters can be collected into a MultiValueMap<String, String> 
    String expected = 
        "key1 values = aaa\n" +
        "key2 values = bbb";
    assertEquals(expected, response);
    
    // Verify that a request parameter can have more that one value.
    response = mockMvc.perform(get("/map?key1=aaa&key2=bbb&key2=ccc"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    expected =
        "key1 values = aaa\n" +
        "key2 values = bbb, ccc";
    assertEquals(expected, response);
  }
  
  @Test
  public void testUserCreation() throws Exception {
    String body = "{ \"username\" : \"test\", \"password\" : \"secret\"}";

    String response = mockMvc.perform(post("/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(body))
        .andExpect(status().is(201)) // 201 created code is a success code indicating we created a new resource.
        .andReturn()
        .getResponse()
        .getContentAsString();
    
    // Verify that a user object was returned in JSON format.
    User user = objectMapper.readValue(response, User.class);
    assertEquals("test", user.getUsername());
    assertEquals("secret", user.getPassword());
  }
  
  @Test
  public void testUserValidation() throws Exception {
    // No username provided.
    String missingUsername = "{ \"password\" : \"secret\" }";
    String missingPassword = "{ \"username\" : \"test\"}";

    mockMvc.perform(post("/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(missingUsername))
        .andExpect(status().is4xxClientError());
    
    mockMvc.perform(post("/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(missingPassword))
        .andExpect(status().is4xxClientError());
  }
  
  @Test
  public void testLocalEnvironment() throws Exception {
    String response = mockMvc.perform(get("/env"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    assertEquals("local", response);
  }

  @RunWith(SpringRunner.class)
  @SpringBootTest
  @AutoConfigureMockMvc
  @ActiveProfiles("heroku")
  public static class HerokuTests {

    @Autowired
    MockMvc mockMvc;
    
    @Test
    public void testHerokuEnvironment() throws Exception {
      String response = mockMvc.perform(get("/env"))
          .andExpect(status().isOk())
          .andReturn()
          .getResponse()
          .getContentAsString();
      
      assertEquals("heroku", response);
    }
  }
  
}


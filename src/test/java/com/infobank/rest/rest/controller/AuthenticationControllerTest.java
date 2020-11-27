package com.infobank.rest.rest.controller;

import java.net.URI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticationControllerTest {

    @Autowired
    MockMvc mock;

    @Test
    public void shouldReturn400InCaseOfWrongAuthData() throws Exception {
        URI uri = new URI("/auth");
        String json = "{ \"email\": \"invalod@email.com\", \"senha\": \"123456\"}";

       mock.perform(MockMvcRequestBuilders.post(uri).content(json)
       .contentType(MediaType.APPLICATION_JSON))
       .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

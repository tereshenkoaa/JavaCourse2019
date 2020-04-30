package com.github.tereshenkoaa.restApp.controller;

import com.github.tereshenkoaa.restApp.RestAppApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestAppApplication.class})
@WebAppConfiguration
public class SessionRestControllerTest {

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    //post-запрос с параметрами, если недоступен, то вернет 404, если доступен, но не передали тело, то 400
    public void testCreate() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/session")
                .accept(MediaType.ALL)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    //get зарпос без параметров, если доступен, то вернет статус 200
    public void testQuestionsNew() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/session/questions-new")
                .accept(MediaType.ALL)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
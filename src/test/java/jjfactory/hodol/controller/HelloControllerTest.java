package jjfactory.hodol.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("컨트롤러 get")
    void get() throws Exception {
        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("컨트롤러 post")
    void post() throws Exception {
        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/hello")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"title\" : \"제목\",\n" +
                                "    \"content\" : \"내용\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Y"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("컨트롤러 post. title 값 필수")
    void post2() throws Exception {
        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/hello")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"content\" : \"내용\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("제목을 입력해주세요"))
                .andDo(MockMvcResultHandlers.print());
    }



}
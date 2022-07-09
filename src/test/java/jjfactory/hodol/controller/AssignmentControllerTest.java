package jjfactory.hodol.controller;

import jjfactory.hodol.domain.Assignment;
import jjfactory.hodol.repository.AssignmentRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest


// @SpringBootTest에서는 MockMvc 못씀. 쓰려면 @AutoConfigureMockMvc 필요

@AutoConfigureMockMvc
@SpringBootTest
class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AssignmentRepository assignmentRepository;


    @BeforeEach
    void clean(){
        assignmentRepository.deleteAll();
    }

    @Test
    @DisplayName("없는 값 조회하면 get Fail")
    void get() throws Exception {
        //given
        Long assignmentId = 1L;

        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/assignments/" + assignmentId))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @DisplayName("get 1개 success")
    void getSuccess() throws Exception {
        //given
        Assignment assignment = Assignment.builder()
                .title("제목~")
                .content("내용!")
                .build();
        assignmentRepository.save(assignment);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/assignments/{assignmentId}",assignment.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value("제목~"))
                .andExpect(jsonPath("$.data.content").value("내용!"))
                .andDo(print());
    }

    @Test
    @DisplayName("get 리스트 / no 페이징")
    void getList() throws Exception {
        //given
        List<Assignment> assignments = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            Assignment assignment = Assignment.builder()
                    .content("내용" + i)
                    .title("제목제목!" + i)
                    .build();

            assignments.add(assignment);
        }
        assignmentRepository.saveAll(assignments);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/assignments")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()", Matchers.is(11)))
                .andExpect(jsonPath("$.data[9].content").value("내용9"))
                .andDo(print());
    }

    @Test
    @DisplayName("get 리스트 페이징")
    void getListPaging() throws Exception {
        //given
        List<Assignment> assignments = IntStream.range(1,31)
                        .mapToObj(i ->Assignment.builder()
                                .content("내용" + i)
                                .title("제목" + i)
                                .build()).collect(Collectors.toList());
        assignmentRepository.saveAll(assignments);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/assignments?page=0&sort=id,desc")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()", Matchers.is(15)))
                .andDo(print());
    }

    @Test
    @DisplayName("컨트롤러 post success")
    void postSuccess() throws Exception {
        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/assignments")
                        .contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "    \"title\" : \"title\",\n" +
                                "    \"content\" : \"content\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Y"))
//                .andExpect(content().string("Y"))
                .andDo(print());

        Assignment assignment = assignmentRepository.findAll().get(0);
        assertEquals(assignment.getContent(),"content");
        assertEquals(assignment.getTitle(),"title");
    }

    @Test
    @DisplayName("컨트롤러 post failed. title 값 필수")
    void postFail() throws Exception {
        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/assignments")
                        .contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "    \"content\" : \"내용\"\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("제목을 입력해주세요"))
                .andDo(print());
    }


}
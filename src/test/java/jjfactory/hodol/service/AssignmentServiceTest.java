package jjfactory.hodol.service;

import jjfactory.hodol.domain.Assignment;
import jjfactory.hodol.repository.AssignmentRepository;
import jjfactory.hodol.req.SubjectCreate;
import jjfactory.hodol.res.AssignmentRes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.IllformedLocaleException;

import static org.assertj.core.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional
class AssignmentServiceTest {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    AssignmentRepository assignmentRepository;

    @BeforeEach
    void clean(){
        assignmentRepository.deleteAll();
    }

    @Test
    void write() {

        SubjectCreate dto = new SubjectCreate("제목", "내용");
        SubjectCreate dto2 = new SubjectCreate("제목", "내용");

        assignmentService.write(dto);
        assignmentService.write(dto2);

        assertThat(assignmentRepository.count()).isEqualTo(2L);
    }

    @Test
    @DisplayName("조회실패시 throw")
    void getFail() {
        //given
        Long assignmentId = 1L;

        //then
        assertThatThrownBy(() -> {assignmentService.get(assignmentId);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("조회 실패");
    }

    @Test
    @DisplayName("조회 성공")
    void getSuccess() {
        //given
        Assignment assignment = Assignment.builder()
                .content("내용")
                .title("제목제목!")
                .build();

        assignmentRepository.save(assignment);
        //when
        AssignmentRes result = assignmentService.get(assignment.getId());

        //then
        assertThat(result.getContent()).isEqualTo("내용");
        assertThat(result.getTitle()).isEqualTo("제목제목!");

    }
}
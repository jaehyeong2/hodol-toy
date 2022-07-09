package jjfactory.hodol.service;

import jjfactory.hodol.domain.Assignment;
import jjfactory.hodol.repository.AssignmentRepository;
import jjfactory.hodol.req.SubjectCreate;
import jjfactory.hodol.res.AssignmentRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("findAll 노 페이징")
    void getList() {
        //given
        List<Assignment> assignments = IntStream.range(1,31)
                .mapToObj(i -> Assignment.builder()
                        .content("내용" + i)
                        .title("제목" + i)
                        .build()).collect(Collectors.toList());
        assignmentRepository.saveAll(assignments);

        Pageable pageable = PageRequest.of(0,10);

        //when
        List<AssignmentRes> result = assignmentService.getList(pageable);
        assertThat(result.size()).isEqualTo(10);

    }

    @Test
    @DisplayName("getList 1페이지 조회")
    void getListPaging() {
        //given
        List<Assignment> assignments = IntStream.range(1,31)
                .mapToObj(i -> Assignment.builder()
                        .content("내용" + i)
                        .title("제목" + i)
                        .build()).collect(Collectors.toList());


        assignmentRepository.saveAll(assignments);

        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"id"));

        //when
        List<AssignmentRes> result = assignmentService.getList(pageable);
        assertThat(result.size()).isEqualTo(10);
        assertThat(result.get(0).getTitle()).isEqualTo("제목30");
        assertThat(result.get(9).getTitle()).isEqualTo("제목21");

    }
}
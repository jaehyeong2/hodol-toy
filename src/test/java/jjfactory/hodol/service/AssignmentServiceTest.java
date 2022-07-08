package jjfactory.hodol.service;

import jjfactory.hodol.repository.AssignmentRepository;
import jjfactory.hodol.req.SubjectCreate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class AssignmentServiceTest {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Test
    void write() {

        SubjectCreate dto = new SubjectCreate("제목", "내용");
        SubjectCreate dto2 = new SubjectCreate("제목", "내용");

        assignmentService.write(dto);
        assignmentService.write(dto2);

        Assertions.assertThat(assignmentRepository.count()).isEqualTo(2L);
    }
}
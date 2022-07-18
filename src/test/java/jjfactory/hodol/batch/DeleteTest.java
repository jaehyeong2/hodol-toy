package jjfactory.hodol.batch;

import jjfactory.hodol.domain.Teacher;
import jjfactory.hodol.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteTest {

    @Autowired
    TeacherRepository teacherRepository;

    @BeforeEach
    void setUp(){
        teacherRepository.deleteAll();
    }

    @Test
    void test(){
        for (int i = 0; i < 10; i++) {
            Teacher build = Teacher.builder().name("teacher" + i).build();
            teacherRepository.save(build);
        }
        teacherRepository.deleteAll();
    }

}

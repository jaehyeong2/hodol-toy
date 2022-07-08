package jjfactory.hodol.service;

import jjfactory.hodol.domain.Student;
import jjfactory.hodol.repository.StudentRepository;
import jjfactory.hodol.req.StudentCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public void userCreate(StudentCreate dto){
        Student student = Student.create(dto);
        studentRepository.save(student);
    }

}

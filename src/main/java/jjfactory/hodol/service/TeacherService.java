package jjfactory.hodol.service;


import jjfactory.hodol.domain.Teacher;
import jjfactory.hodol.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public void create(){

    }

}

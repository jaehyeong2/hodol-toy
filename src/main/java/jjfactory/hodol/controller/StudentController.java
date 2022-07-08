package jjfactory.hodol.controller;

import jjfactory.hodol.req.StudentCreate;
import jjfactory.hodol.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {
    private final StudentService studentService;

    @PostMapping("")
    public void create(@RequestBody StudentCreate studentCreate){

    }
}

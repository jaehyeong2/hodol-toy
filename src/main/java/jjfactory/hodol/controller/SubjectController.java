package jjfactory.hodol.controller;

import jjfactory.hodol.req.SubjectCreate;
import jjfactory.hodol.res.ApiRes;
import jjfactory.hodol.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SubjectController {

    private final AssignmentService assignmentService;

    @PostMapping("/hello")
    public ApiRes<String> get(@RequestBody @Valid SubjectCreate dto){
        return new ApiRes<>(assignmentService.write(dto));
    }
}

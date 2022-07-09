package jjfactory.hodol.controller;

import jjfactory.hodol.req.SubjectCreate;
import jjfactory.hodol.res.ApiRes;
import jjfactory.hodol.res.AssignmentRes;
import jjfactory.hodol.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/assignments")
@RestController
public class AssignmentController {
    private final AssignmentService assignmentService;

    @GetMapping("/{assignmentId}")
    public ApiRes<AssignmentRes> get(@PathVariable Long assignmentId){
        return new ApiRes<>(assignmentService.get(assignmentId));
    }

    @PostMapping("")
    public ApiRes<String> post(@RequestBody @Valid SubjectCreate dto){
        return new ApiRes<>(assignmentService.write(dto));
    }
}

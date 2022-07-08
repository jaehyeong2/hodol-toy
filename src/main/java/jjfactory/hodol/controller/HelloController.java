package jjfactory.hodol.controller;

import antlr.StringUtils;
import jjfactory.hodol.req.PostCreate;
import jjfactory.hodol.res.ApiRes;
import jjfactory.hodol.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloController {

    private final PostService postService;

    @PostMapping("/hello")
    public ApiRes<String> get(@RequestBody @Valid PostCreate dto){
        return new ApiRes<>(postService.write(dto));
    }
}

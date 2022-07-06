package jjfactory.hodol.controller;

import jjfactory.hodol.req.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class HelloController {

    @PostMapping("/hello")
    public String get(@RequestBody PostCreate params){
        log.info("params = {}", params.toString());
        return "Y";
    }
}

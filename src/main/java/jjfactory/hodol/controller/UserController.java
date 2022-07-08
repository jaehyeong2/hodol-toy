package jjfactory.hodol.controller;

import jjfactory.hodol.req.StudentCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
//    private final UserService userService;

    @PostMapping("")
    public void create(@RequestBody StudentCreate studentCreate){

//        userService.userCreate(userCreate);
    }
}

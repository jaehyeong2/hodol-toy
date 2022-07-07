package jjfactory.hodol.controller;

import antlr.StringUtils;
import jjfactory.hodol.req.PostCreate;
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
@RestController
public class HelloController {

    @PostMapping("/hello")
    public Map<String,String> get(@RequestBody @Valid PostCreate params, BindingResult result){
        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField();
            String defaultMessage = firstFieldError.getDefaultMessage();

            Map<String,String> error = new HashMap<>();
            error.put(fieldName,defaultMessage);
            return error;
        }
        return Map.of();
    }
}

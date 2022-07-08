package jjfactory.hodol.controller;

import jjfactory.hodol.res.ErrorRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorRes invalidReqHandler(MethodArgumentNotValidException e){
        ErrorRes errorRes = ErrorRes.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        for(FieldError error : e.getFieldErrors()){
            errorRes.addValidation(error.getField(),error.getDefaultMessage());
        }

        return errorRes;
    }
}

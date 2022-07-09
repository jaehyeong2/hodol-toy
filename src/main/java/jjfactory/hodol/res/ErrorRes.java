package jjfactory.hodol.res;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorRes {
    private final String code;
    private final String message;
    private final Map<String,String> validation = new HashMap<>();

    @Builder
    public ErrorRes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void addValidation(String fieldError, String errorMessage){
        validation.put(fieldError,errorMessage);
    }

//    @RequiredArgsConstructor
//    private class ValidationTuple{
//        private final String fieldName;
//        private final String errorMessage;
//    }
}

package jjfactory.hodol.res;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ErrorRes {
    private final String code;
    private final String message;

    private final Map<String,String> validation = new HashMap<>();

    public void addValidation(String fieldError, String errorMessage){
        validation.put(fieldError,errorMessage);
    }

//    @RequiredArgsConstructor
//    private class ValidationTuple{
//        private final String fieldName;
//        private final String errorMessage;
//    }
}

package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Exception.AppException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice

public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {AppException.class})
    protected ResponseEntity< Object > handleConflict(AppException ex) {
        return ResponseEntity.ok().headers((HttpHeaders) null).body(new AppException(ex.getMessage(),ex.getCode()));

    }

}

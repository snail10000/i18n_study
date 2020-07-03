package com.xzstudy.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 *
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private Messageutil messageutil;


    @ExceptionHandler(BindException.class)
    public ResponseEntity handle( BindException e){
       String key = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageutil.getLocaleMessage(key,null,null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle1( MethodArgumentNotValidException e){
       String key = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageutil.getLocaleMessage(key,null,null));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handle2(ConstraintViolationException e){
        String key = e.getConstraintViolations().iterator().next().getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageutil.getLocaleMessage(key,null,null));
    }


    @ExceptionHandler(Exception.class)
    public Map exceptionHandler(Exception e){
        Map hashMap = new HashMap();
        ConstraintViolationException con = (ConstraintViolationException) e;
        Set<ConstraintViolation<?>> constraintViolations = con.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String message = constraintViolation.getMessage();
            System.out.println(message);
        }
        String localizedMessage = con.getLocalizedMessage();
        hashMap.put("msg",localizedMessage.split(":")[1]);
        return hashMap;
    }

}
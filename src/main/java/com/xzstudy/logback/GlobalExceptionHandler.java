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

//    @Autowired
//    private MessageSource messageSource;
//
//    @Autowired
//    private HttpServletRequest request;
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
//        FieldError fieldError = e.getBindingResult().getFieldError();
//        log.error(fieldError.getField() + ":" + fieldError.getDefaultMessage());
//        Map hashMap = new HashMap();
//        String message = e.getMessage();
//        hashMap.put("msg",message);
//        return hashMap;
//    }


    @ExceptionHandler(BindException.class)
    public ResponseEntity handle( BindException e){
       String key = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageutil.getLocaleMessage(key,null,new String []{"我将把你替换"}));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle1( MethodArgumentNotValidException e){
       String key = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageutil.getLocaleMessage(key,null,new String[]{"我将把你替换"}));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handle2(ConstraintViolationException e){
        String key = e.getConstraintViolations().iterator().next().getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageutil.getLocaleMessage(key,null,new String[]{"我将把你替换"}));
    }


//    @ExceptionHandler(BindException.class)
//    public Map BindExceptionHandler(BindException e) {
//        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
//        return new HashMap();
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public Map ConstraintViolationExceptionHandler(ConstraintViolationException e) {
//        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
//        return  new HashMap();
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
//        FieldError fieldError = e.getBindingResult().getFieldError();
//        log.error(fieldError.getField() + ":" + fieldError.getDefaultMessage(),e);
//        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
//        return new HashMap();
//    }

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


    /**
     * 获取国际化异常信息
     */
//    private String getLocaleMessage(String code, String defaultMsg, Object[] params) {
//        String language = request.getParameter("lang");
//        Locale locale = Objects.nonNull(language) ? new Locale(language) : Locale.getDefault();
//        try {
//            return messageSource.getMessage(code, params, locale);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.warn("本地化异常消息发生异常: {}, {}", code, params);
//            return defaultMsg;
//        }
//    }
}
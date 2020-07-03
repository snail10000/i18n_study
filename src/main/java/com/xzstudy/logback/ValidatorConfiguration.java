package com.xzstudy.logback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 快速失败模式: 当有一个条件不符合时直接返回
 */
@Configuration
public class ValidatorConfiguration {


    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation
                .byProvider( HibernateValidator.class )
                .configure()
                .failFast( true )//快速失败配置
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }



    public static void main(String[] args) throws JsonProcessingException {
        String jsonStr ="{\n" +
                "    \"name\":\"张三\",\n" +

                "    \"email\":\"123@qq.com\"\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(jsonStr, User.class);
        System.out.println();
    }
}
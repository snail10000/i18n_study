package com.xzstudy.logback;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
package com.xzstudy.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


/**
 * 获取国际化信息工具类
 */
@Slf4j
@Component
public class Messageutil {

    @Autowired
    private MessageSource messageSource;


    /**
     * 根据code获取国际化信息
     * @param code code
     * @return
     */
    public String getLocaleMessage(String code) {
        return  getLocaleMessage(code,null,null);
    }

    /**
     * 根据code获取国际化信息,如果没有则使用默认提示信息
     * @param code code
     * @param defaultMsg 默认提示信息
     * @return
     */
    public String getLocaleMessage(String code, String defaultMsg) {
        return  getLocaleMessage(code,defaultMsg,null);
    }

    /**
     * 根据code获取国际化信息,并且替换占位符
     * @param code
     * @param params
     * @return
     */
    public String getLocaleMessage(String code, String[] params) {
        return  getLocaleMessage(code,null,params);
    }


    /**
     * 根据code获取国际化信息,没有就使用默认值,并且替换占位符
     * @param code code
     * @param defaultMsg 发生错误时返回的默认信息
     * @param params 替换占位符的参数
     * @return
     */
    public String getLocaleMessage(String code, String defaultMsg, Object[] params) {
        try {
            return messageSource.getMessage(code, params, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("本地化异常消息发生异常: {}, {}", code, params);
            return defaultMsg;
        }
    }

}
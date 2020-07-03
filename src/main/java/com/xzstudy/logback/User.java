package com.xzstudy.logback;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 *
 *user 实体类型
 */
@Data
public class User {
    @NotBlank(groups = {Add.class},message = "111")
    private String name;

    @NotEmpty(groups = Editor.class,message = "222")
    private String addr;

    @Email
    @NotBlank
    private String email;
}
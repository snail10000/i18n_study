package com.xzstudy.logback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;


@Validated
@RestController
public class TestController {

    @Autowired
    private Messageutil messageutil;
    @Autowired
    private HttpServletRequest request;
    /**
     * 对单个属性进行检查
     * @param name
     * @param addr
     * @return
     */
    @PostMapping("/registerParams")
    public Map registerParams(@NotEmpty(message = "111") String name, @NotEmpty(message = "222") String addr){
        System.out.println(request.getClass());
        Map<String,Object> result = new HashMap();
        result.put("data","ok");
        return result;
    }

    /**
     * 新增
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public Map addUser( @RequestBody User user){
        Map<String,Object> result = new HashMap();
        result.put("data",messageutil.getLocaleMessage("add.ok"));
        return result;
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public Map updateUser(@Validated(Editor.class) @RequestBody User user){
        Map<String,Object> result = new HashMap();
        result.put("data","修改成功");
        return result;
    }

}
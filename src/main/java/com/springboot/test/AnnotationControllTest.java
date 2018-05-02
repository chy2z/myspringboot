package com.springboot.test;

import com.springboot.Annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @Title: AnnotationControllTest
* @Description: 注解测试
* @author chy
* @date 2018/4/27 10:41
*/
@MYAnnotation(id=1,msg = "自定义注解")
@MYController("/my")
public class AnnotationControllTest {

    @MYAutowired(required = false)
    AnnotationControllTest field;

    @MYRequestMapping(path = {"/default","/login"},method = {RequestMethod.GET,RequestMethod.POST},name = "入口")
    public String index(@MYRequestParam(name="id",required = true) String id) {
        return "";
    }

}

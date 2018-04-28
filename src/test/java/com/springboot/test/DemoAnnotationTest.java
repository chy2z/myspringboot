package com.springboot.test;

import com.springboot.Annotation.MYAnnotation;
import com.springboot.Annotation.MYAutowired;
import com.springboot.Annotation.MYController;
import com.springboot.Annotation.MYRequestMapping;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
* @Title: DemoAnnotationTest
* @Description: 注解测试
* @author chy
* @date 2018/4/27 11:28
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoAnnotationTest {

    @Test
    public void run() {

        // 是否有MYAnnotation类注解
        boolean hasMyAnnotation = AnnotationControllTest.class.isAnnotationPresent(MYAnnotation.class);
        if(hasMyAnnotation) {
            MYAnnotation myAnnotation = AnnotationControllTest.class.getAnnotation(MYAnnotation.class);
            System.out.println("类注解|id-->:" + myAnnotation.id());
            System.out.println("类注解|msg-->:" + myAnnotation.msg());
        }

        // 是否有MYController类注解
        boolean hasControllAnnotation = AnnotationControllTest.class.isAnnotationPresent(MYController.class);
        if(hasControllAnnotation){
            MYController myController = AnnotationControllTest.class.getAnnotation(MYController.class);
            System.out.println("类注解|value-->:"+myController.value());
        }

        // 字段注解
        try {
            Field field = AnnotationControllTest.class.getDeclaredField("field");
            field.setAccessible(true);
            MYAutowired myAutowired=field.getAnnotation(MYAutowired.class);
            if(myAutowired!=null) {
                System.out.println("字段注解|value-->:" + (myAutowired.required() ? "true" : "false"));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // 方法注解
        try {
           Method method= AnnotationControllTest.class.getDeclaredMethod("index",String.class);
           if(method!=null){
               MYRequestMapping requestMapping=  method.getAnnotation(MYRequestMapping.class);
               if(requestMapping!=null){
                   String[] paths=requestMapping.path();
                   for(String p : paths){
                       System.out.println("方法注解|path-->:" +p);
                   }
                   RequestMethod[] methods=requestMapping.method();
                   for(RequestMethod m : methods){
                       System.out.println("方法注解|method-->:" +m.name());
                   }
               }
           }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}

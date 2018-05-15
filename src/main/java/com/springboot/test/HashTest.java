package com.springboot.test;

import com.springboot.model.RequestResult;

/**
* @Title: HashTest
* @Description:  hash 测试
* @author chy
* @date 2018/5/2 9:30
*/
public class HashTest {

    public static void main(String[] arg) {

        Integer i = 11111;
        System.out.println(i.hashCode());

        Long l = 999L;
        System.out.println(l.hashCode());

        Byte b=0x7f;
        System.out.println(b.hashCode());

        String s1="chy";
        String s2="chy";
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        char c='A';
        System.out.println((int)c);

        RequestResult r1=new RequestResult();
        RequestResult r2=new RequestResult();
        System.out.println(r1.hashCode());
        System.out.println(r2.hashCode());
        r2=r1;
        System.out.println(r2.hashCode());
    }

}

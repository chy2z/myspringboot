package com.springboot.test;

/**
* @Title: StaticCodeTest
* @Description: 静态代码
* @author chy
* @date 2018/4/26 15:55
*/
public class StaticCodeTest {

    /* 静态变量 */
    public static String staticField = "静态变量";

    /* 变量 */
    public String field = "变量";

    /* 静态初始化块 只执行一次 */
    static {
        System.out.println( "静态初始化块 开始" );
        System.out.println( staticField );
        System.out.println( "静态初始化块 结束" );
    }

    /* 初始化块 */
    {
        System.out.println( "初始化块 开始" );
        System.out.println( field );
        System.out.println( "初始化块 结束" );
    }

    /* 构造器 */
    public StaticCodeTest()
    {
        System.out.println( "构造器" );
    }

    /**
     * 静态变量、静态初始化块）>（变量、初始化块）>构造器
     * @param args
     */
    public static void main( String[] args )
    {
        System.out.println( "main 开始" );
        new StaticCodeTest();
        new StaticCodeTest();
        System.out.println( "main 结束" );
    }
}

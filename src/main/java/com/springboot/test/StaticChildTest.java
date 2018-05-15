package com.springboot.test;

/**
* @Title: StaticChildTest
* @Description: 静态代码测试-子静态类
* @author chy
* @date 2018/4/26 16:13
*/
public class StaticChildTest extends StaticParentTest {

    /* 静态变量 */
    public static String s_StaticField = "子类--静态变量";

    /* 变量 */
    public String s_Field = "子类--变量";

    /* 静态初始化块 */
    static {
        System.out.println( s_StaticField );
        System.out.println( "子类--静态初始化块" );
    }

    /* 初始化块 */
    {
        System.out.println( s_Field );
        System.out.println( "子类--初始化块" );
    }

    /* 构造器 */
    public StaticChildTest()
    {
        System.out.println( "子类--构造器" );
        System.out.println( "i=" + i + ",j=" + j );
    }

    /**
     * 父类（静态变量、静态初始化块）-->子类（静态变量、静态初始化块）-->
     * 父类（变量、初始化块）--> 父类构造器 -->子类（变量、初始化块）-->子类构造器
     * @param args
     */
    public static void main( String[] args )
    {
        new StaticChildTest();
        System.out.println( "==========================" );
        new StaticChildTest();
    }

}

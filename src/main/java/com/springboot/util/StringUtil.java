package com.springboot.util;

/**
* @Title: StringUtil
* @Description: 字符串处理
* @author chy
* @date 2017/10/22 21:27
*/
public class StringUtil {

    /**
     * 字符串为 null or 字符串
     * 不能为 ""和"null"
     * @param str
     * @return
     */
    public static String nullOrString(String str) {
        if (null == str || str.equals("") || str.toUpperCase().equals("NULL")) {
            return null;
        } else {
            return str;
        }
    }

    /**
     * 字符串  "" or 字符串
     * @param str
     * @return
     */
    public static String emptyOrString(String str) {
        if (null == str || str.equals("") || str.toUpperCase().equals("NULL")) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * 判断是非空字符串
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str){
        if(str==null||str.equals("")|| str.toUpperCase().equals("NULL")) {
            return false;
        }
        else {
            return true;
        }
    }

}
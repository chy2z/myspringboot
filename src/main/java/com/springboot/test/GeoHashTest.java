package com.springboot.test;

import com.springboot.util.GeoHashUtil;

/**
* @Title: GeoHashTest
* @Description: GeoHash 测试
* @author chy
* @date 2018/5/30 10:14
*/
public class GeoHashTest {

    public static void main(String[] args) {

        GeoHashUtil g = new GeoHashUtil(40.222012, 116.248283,8);

        System.out.println("geoHash:"+g.getGeoHashBase32());

        //计算附近相邻的8个geoHash
        g.getGeoHashBase32For9().forEach(s -> System.out.println("附近geoHash:"+s));
    }
}

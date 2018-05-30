package com.springboot.model;

/** 
* @Title: GeoLocation
* @Description: 存储经纬度信息
* @author chy
* @date 2018/5/30 9:32 
*/
public class GeoLocation {
    public static final double MINLAT = -90;
    public static final double MAXLAT = 90;
    public static final double MINLNG = -180;
    public static final double MAXLNG = 180;

    /**
     * 纬度区间[-90,90]
     */
    private double lat;

    /**
     * 经度区间[-180,180]
     */
    private double lng;

    public GeoLocation(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
}

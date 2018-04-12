package com.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/8/31.
 */
@Component
@ConfigurationProperties(prefix = "ImageType")
public class ImageTypeProperties {

    private String jpg;

    private String bmp;

    private String png;

    public String getJpg() {
        return jpg;
    }

    public void setJpg(String jpg) {
        this.jpg = jpg;
    }

    public String getBmp() {
        return bmp;
    }

    public void setBmp(String bmp) {
        this.bmp = bmp;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }
}
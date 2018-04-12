package com.springboot.fastFDS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 配置文件
 */
@Configuration
public class FastFDSConfig {

    @Value("${fastdfs.connect_timeout_in_seconds}")
    public String connect_timeout_in_seconds;

    @Value("${fastdfs.network_timeout_in_seconds}")
    public String network_timeout_in_seconds;

    @Value("${fastdfs.charset}")
    public String charset;

    @Value("${fastdfs.http_anti_steal_token}")
    public String http_anti_steal_token;

    @Value("${fastdfs.http_secret_key}")
    public String http_secret_key;

    @Value("${fastdfs.http_tracker_http_port}")
    public String http_tracker_http_port;

    @Value("${fastdfs.tracker_servers}")
    public String tracker_servers;

    /**
     * 获取配置
     * @return
     */
    public Properties getConfig() {
        Properties popers = new Properties();
        popers.setProperty("fastdfs.connect_timeout_in_seconds", connect_timeout_in_seconds);
        popers.setProperty("fastdfs.network_timeout_in_seconds", network_timeout_in_seconds);
        popers.setProperty("fastdfs.charset", charset);
        popers.setProperty("fastdfs.http_anti_steal_token", http_anti_steal_token);
        popers.setProperty("fastdfs.http_secret_key", http_secret_key);
        popers.setProperty("fastdfs.http_tracker_http_port", http_tracker_http_port);
        popers.setProperty("fastdfs.tracker_servers", tracker_servers);
        return popers;
    }
}

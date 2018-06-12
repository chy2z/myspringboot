package com.springboot.hBase;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

@Configuration
public class HBaseConfig {

    /**
     * 创建HbaseTemplate
     * @param quorum
     * @param port
     * @return
     */
    @Bean
    public HbaseTemplate getHbaseTemplate(
            @Value("{hbase.zookeeper.quorum}") String quorum,
            @Value("{hbase.zookeeper.port}") String port
            ){
        HbaseTemplate hbaseTemplate = new HbaseTemplate();
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", quorum);
        conf.set("hbase.zookeeper.port", port);
        hbaseTemplate.setConfiguration(conf);
        hbaseTemplate.setAutoFlush(true);
        hbaseTemplate.setEncoding("utf-8");
        return hbaseTemplate;
    }

}

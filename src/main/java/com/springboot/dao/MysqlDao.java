package com.springboot.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2017/8/10.
 */
@Repository
public class MysqlDao extends  BaseDao {

    public int update() {
        String sql = "update test set nickname = ? where id = ?";
        Object[] params = new Object[]{"张三",1};
        return jdbcTemplate.update(sql, params);
    }

    public List queryForList() {
        String sql = "select id ,name,nickname from test where id = ?";
        Object[] params = new Object[]{1};
        return jdbcTemplate.queryForList(sql, params);
    }
}

package com.springboot.concurrency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
* @Title: ConcurrentQuerySqlTask
* @Description: 并发数据库查询任务
* @author chy
* @date 2018/3/23 16:57
*/
public class ConcurrentQuerySqlTask extends RecursiveTask<Map<String, Object>> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public Map<String, Object> result;

    public List<String> querySqls;

    public ConcurrentQuerySqlTask(List<String> sqls) {
        this.querySqls=sqls;
    }

    @Override
    protected Map<String, Object> compute() {

        /**
         * 子任务
         */
        List<ConcurrentQuerySqlTask> subTasks = new ArrayList<>();

        List<String> subQuerySqls=null;

        Map<String, Object> subResult=new HashMap<>();

        /**
         * 分解sql查询任务
         */
        if(querySqls.size()>1) {
            for (String sql : querySqls) {
                subQuerySqls = new ArrayList<>();
                subQuerySqls.add(sql);
                subTasks.add(new ConcurrentQuerySqlTask(subQuerySqls));
            }

            // 在当前的 ForkJoinPool 上调度所有的子任务。
            if (!subTasks.isEmpty()) {
                Map<String, Object> result;
                for (ConcurrentQuerySqlTask subTask : invokeAll(subTasks)) {
                    //获取每个线程的结果
                    result = subTask.join();
                    if(result!=null)
                    {
                        //存储结果
                        for(Map.Entry<String, Object> vo : result.entrySet()){
                            subResult.put(vo.getKey(),vo.getValue());
                        }
                    }
                }
            }

            return subResult;
        }
        else {
            //使用查询语句当key
            subResult.put(String.valueOf(querySqls.get(0).hashCode()),"查询结果");
            return subResult;
        }
    }

}
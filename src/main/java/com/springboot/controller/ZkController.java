package com.springboot.controller;

import com.springboot.util.ZkWatchUtil;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/** 
* @Title: ZkController
* @Description:
* @author chy
* @date 2018/4/12 13:45 
*/
@RestController
@EnableAutoConfiguration
@RequestMapping("/zkp")
public class ZkController {

    public static final Logger LOG = LoggerFactory.getLogger(ZkController.class);

    /**
     * 入门例子
     * 如果出现连接错误：
     * 配置  windows/System32/drivers/etc/hosts
     * 127.0.0.1       admin-PC
     * 127.0.0.1       localhost
     *
     * admin-PC:2181
     * 127.0.0.1:2181
     * @return
     */
    @RequestMapping("/test")
    public String test() {
        //路径必须 /
        String path = "/dmc";

        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                LOG.info("收到事件通知：State->" + watchedEvent.getState()
                        + ",Type->：" + watchedEvent.getType()
                        + ",Path->：" + watchedEvent.getPath());
                if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {

                }
                if (Event.EventType.NodeCreated == watchedEvent.getType()) {

                }
                if (path.equals(watchedEvent.getPath())) {

                }
            }
        };

        try {
            //创建ZooKeeper实例
            ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 5000, watcher);

            //节点存先删除
            if (zk.exists(path, watcher) != null) {
                zk.delete(path, -1);
            }

            //创建一个节点,模式是PERSISTENT
            zk.create(path, "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            //读取数据
            System.out.println("创建节点" + path + ",数据为：" + new String(zk.getData(path, watcher, null)));

            //修改节点数据
            zk.setData(path, "2".getBytes(), -1);

            int i = 1;

            while (i <= 20) {

                //读取数据
                System.out.println("修改节点" + path + ",数据为：" + new String(zk.getData(path, watcher, null)));

                Thread.sleep(1000);

                i++;
            }

            //删除一个节点
            System.out.println(zk.exists(path, watcher));

            zk.delete(path, -1);

            //节点是否存在
            System.out.println(zk.exists(path, watcher));

            //连接关闭
            zk.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return "test";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/tool")
    public String tool(){

        //路径必须 /
        String path="/zkChy";

        String node1="/zkChy/node1";

        String node2="/zkChy/node2";

        ZkWatchUtil zkWatchUtil=new ZkWatchUtil();

        //连接服务器
        zkWatchUtil.connectionZookeeper("127.0.0.1:2181",10000);

        //必须删除子节点才能删除父节点
        if(zkWatchUtil.isExists(node1)) {
            zkWatchUtil.deletePath(node1);
        }
        if(zkWatchUtil.isExists(node2)) {
            zkWatchUtil.deletePath(node2);
        }
        if(zkWatchUtil.isExists(path)) {
            zkWatchUtil.deletePath(path);
        }

        //节点不能重复创建
        zkWatchUtil.createPath(path,"黑暗行动");

        zkWatchUtil.createPath(node1,"黑暗行动-1");

        zkWatchUtil.createPath(node2,"黑暗行动-1");

        //读取数据
        System.out.println("创建节点" + path + ",数据为：" + zkWatchUtil.readData(path));

        zkWatchUtil.writeData(path,"黑暗行动-0");

        //读取数据
        System.out.println("修改节点" + path + ",数据为：" + zkWatchUtil.readData(path));

        List<String> ls =zkWatchUtil.getChild(path);

        if(!ls.isEmpty()) {
            for (String node : ls) {
                System.out.println("读取子节点" + node + ",数据为：" + zkWatchUtil.readData(path+"/"+node));
                zkWatchUtil.deletePath(path+"/"+node);
            }
        }

        //必须删除子节点才能删除父节点
        zkWatchUtil.deletePath(path);

        zkWatchUtil.releaseConnection();

        return "tool";
    }
}

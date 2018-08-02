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
                System.out.println("开始执行process方法-----event:"+watchedEvent);

                if(watchedEvent == null){
                    return;
                }

                //取得连接状态
                Event.KeeperState state = watchedEvent.getState();
                //取得事件类型
                Event.EventType eventType = watchedEvent.getType();

                //哪一个节点路径发生变更
                String nodePath = watchedEvent.getPath();
                String log_process = "Watcher";
                System.out.println(log_process+"收到事件通知");
                System.out.println(log_process+"连接状态："+state);
                System.out.println(log_process+"事件类型："+eventType);
                System.out.println(log_process+"节点路径："+nodePath);

                //连接成功
                if(Event.KeeperState.SyncConnected == state ){

                    // 没有任何节点，表示创建连接成功(客户端与服务器端创建连接成功后没有任何节点信息)
                    if(Event.EventType.None == watchedEvent.getType()){
                        System.out.println(log_process+"成功链接zookeeper服务器");
                    }
                    //当服务器端创建节点的时候触发
                    else if(Event.EventType.NodeCreated == watchedEvent.getType()){
                        System.out.println(log_process+" zookeeper服务端创建新的节点");
                    }
                    //被监控该节点的数据发生变更的时候触发
                    else if(Event.EventType.NodeDataChanged == watchedEvent.getType()){
                        System.out.println(log_process+"节点的数据更新");
                    }
                    else if(Event.EventType.NodeChildrenChanged == watchedEvent.getType()){
                        // 对应本代码而言只能监控根节点的一级节点变更。如：在根节点直接创建一级节点，
                        //或者删除一级节点时触发。如修改一级节点的数据，不会触发，创建二级节点时也不会触发。
                        System.out.println("子节点发生变更");
                    }
                    else if(Event.EventType.NodeDeleted == watchedEvent.getType()){
                        System.out.println(log_process+"节点："+nodePath+"被删除");
                    }

                }
                else if(Event.KeeperState.Disconnected == state){
                    System.out.println(log_process+"客户端连接zookeeper服务器端失败");
                }
                else if(Event.KeeperState.Expired == state){
                    System.out.println(log_process+"客户端与zookeeper服务器端会话失败");
                }
                else if(Event.KeeperState.AuthFailed == state){
                    System.out.println(log_process+"权限认证失败");
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

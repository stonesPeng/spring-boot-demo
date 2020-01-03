package com.stone.zookeeper.app;

import com.stone.zookeeper.zk.ZKManager;
import com.stone.zookeeper.zk.ZKManagerImpl;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * @author honorstone
 * @date 2019/8/20 15:49
 */
public class ZooKeeperApp {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZKManager zkManager = new ZKManagerImpl();
        //zkManager.create("/SecondZNode","我们的歌".getBytes());
        Object nodeDate = zkManager.getZNodeData("/SecondZNode", true);
        if(nodeDate instanceof  String){
            System.out.println(nodeDate);
        }
    }
}

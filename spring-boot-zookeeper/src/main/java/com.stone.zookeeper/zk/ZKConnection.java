package com.stone.zookeeper.zk;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author honorstone
 * @date 2019/8/20 15:30
 */
public class ZKConnection {
    private ZooKeeper zoo;
    CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZooKeeper connect(String host) throws IOException, InterruptedException {
        zoo = new ZooKeeper(host, 5000,null);
        return zoo;
    }

    public void  close() throws InterruptedException {
        zoo.close();
    }
}

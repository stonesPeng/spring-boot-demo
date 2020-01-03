package com.stone.zookeeper.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author honorstone
 * @date 2019/8/20 15:42
 */
public class ZKManagerImpl implements ZKManager {

    private static ZooKeeper zooKeeper;
    private static ZKConnection zkConnection;

    public ZKManagerImpl() throws IOException, InterruptedException {
        initialize();
    }

    private void initialize() throws IOException, InterruptedException {
        zkConnection = new ZKConnection();
        zooKeeper = zkConnection.connect("192.168.99.101:2181");
    }
    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
        zooKeeper.create(path,
                data,
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
    }

    public Object getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException, UnsupportedEncodingException {
        byte[] b = null;
        b = zooKeeper.getData(path,null,null);
        return new String(b,"UTF-8");
    }

    public void update(String path, byte[] data) throws KeeperException, InterruptedException {
        int version = zooKeeper.exists(path,true).getVersion();
        zooKeeper.setData(path,data,version);
    }
}

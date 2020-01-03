package com.stone.zookeeper.zk;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author honorstone
 * @date 2019/8/20 15:35
 */
public interface ZKManager {
    void create(String path,byte[]  data) throws KeeperException, InterruptedException, IOException;
    Object getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException, UnsupportedEncodingException;
    void update(String path, byte[] data) throws KeeperException, InterruptedException;
}

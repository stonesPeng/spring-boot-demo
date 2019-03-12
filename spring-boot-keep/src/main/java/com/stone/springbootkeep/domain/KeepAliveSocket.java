package com.stone.springbootkeep.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author honorstone
 * @date 2019/3/12 10:06
 */
public class KeepAliveSocket {

    @Getter
    private String serverName;
    private Socket aliveSocket;
    private boolean isAlive = false;
    private String host;
    private int port;
    private int timeout;
    private boolean loop;
    private long sendTime = 0;
    private long delayTime = 5 * 1000;
    private OutputStream os ;
    private Thread thread ;

    public KeepAliveSocket(String pHost,int pPort,int pTimeout,String pServerName){
        this.host = pHost;
        this.port = pPort;
        this.timeout = pTimeout;
        this.serverName = pServerName;
    }

    public synchronized  boolean isAlive(){
        return isAlive;
    }

    public synchronized void setAlive(boolean alive){
        isAlive = alive;
    }

    public void disConnect(){

    }

    public void connectServer(){

    }

    class SocketClient implements Runnable{

        @Override
        public void run() {
            while (loop){
                if(null == aliveSocket){
                    try {
                        if(Strings.isNotEmpty(serverName)){
                            System.out.println("try to connect server ...");
                            os = aliveSocket.getOutputStream();
                            aliveSocket.setSoTimeout(timeout);
                            setAlive(true);
                            System.out.println("connect server success");
                        }
                        aliveSocket = new Socket(host,port);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

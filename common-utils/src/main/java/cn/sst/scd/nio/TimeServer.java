package cn.sst.scd.nio;

import cn.sst.scd.nio.runnable.MultiplexerTimeServer;

import java.io.IOException;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/12/1 上午9:23
 * @Version 1.1.0
 **/
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8090;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        //MultiplexerTimeServer的多路复用类，它是个一个独立的线程，
        //负责轮询多路复用器Selector，可以处理多个客户端的并发接入。
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}

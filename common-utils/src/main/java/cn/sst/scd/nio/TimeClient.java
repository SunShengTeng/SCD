package cn.sst.scd.nio;

import cn.sst.scd.nio.runnable.TimeClientHandle;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/12/1 上午11:17
 * @Version 1.1.0
 **/
public class TimeClient {
    public static void main(String[] args) {
        int port = 8090;
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient- 001").start();
    }
}

package cn.sst.scd.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shengtengsun
 * @Description 线程池工具类
 * @Date 2020/9/28 4:33 下午
 * @Version 1.1.0
 **/
public class ThreadPoolUtil {

    public static final int CORE_POOL_SIZE = 5;
    public static final int MAXIMUM_POOL_SIZE = 10;
    public static final int KEEP_ALIVE_TIME = 10;

    public static final int BLOCKING_QUEUE_SIZE = 20;

    private volatile static ThreadPoolExecutor executor;

    private ThreadPoolUtil() {
    }

    public static ThreadPoolExecutor createLogThreadPoolExecutor() {
        if (null == executor) {
            synchronized (ThreadPoolUtil.class) {
                if (null == executor) {
                    executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                            MAXIMUM_POOL_SIZE,
                            KEEP_ALIVE_TIME,
                            TimeUnit.MINUTES,
                            new LinkedBlockingDeque<Runnable>(BLOCKING_QUEUE_SIZE),
                            Executors.defaultThreadFactory());
                }
            }
        }
        return executor;
    }

    public static ThreadPoolExecutor itemServiceThreadPoolExecutor() {
        if (null == executor) {
            synchronized (ThreadPoolUtil.class) {
                if (null == executor) {
                    executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                            MAXIMUM_POOL_SIZE,
                            KEEP_ALIVE_TIME,
                            TimeUnit.MINUTES,
                            new LinkedBlockingDeque<Runnable>(BLOCKING_QUEUE_SIZE),
                            Executors.defaultThreadFactory());
                }
            }
        }
        return executor;
    }
}

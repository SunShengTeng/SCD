package cn.sst.scd.nio;

import cn.sst.scd.nio.handler.ItemSelectorHandler;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/12/1 下午5:01
 * @Version 1.1.0
 **/
public class ItemNioClient {
    private static final Logger logger = LoggerFactory.getLogger(ItemNioClient.class);

    public static void startClient() {
        Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("ItemClient-Pool-").build())
                .submit(new ItemSelectorHandler());
        logger.info("start ItemNioClient success ! ! ! ");
    }
}

package cn.sst.scd.nio;

import cn.sst.scd.nio.handler.InventorySelectorHandler;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.Executors;

/**
 * @author shengtengsun
 * @Description 商品Nio服务
 * @Date 2020/12/1 下午2:41
 * @Version 1.1.0
 **/
public class InventoryNioServer {

    private static final Logger logger = LoggerFactory.getLogger(InventoryNioServer.class);

    private static final Integer SERVER_PORT = 8090;

    private static Selector serverSelector;

    public static void main(String[] args) throws IOException {
        // 初始化资源
        InventoryNioServer server = new InventoryNioServer(SERVER_PORT);
        // 启动守护进程，并监听连接
        Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("ItemNioServer-Pool-").build())
                .submit(new InventorySelectorHandler(InventoryNioServer.serverSelector));
    }

    public InventoryNioServer(Integer port) throws IOException {
        // 1、创建服务器资源，并注册
        ServerSocketChannel server = ServerSocketChannel.open();
        InventoryNioServer.serverSelector = Selector.open();
        // 2、绑定网络地址新，并设置为同步非阻塞
        server.socket().bind(new InetSocketAddress(port));
        server.configureBlocking(false);
        // 3、注册到Selector，并监听accept
        server.register(InventoryNioServer.serverSelector, SelectionKey.OP_ACCEPT);
        logger.info("ItemNioServer is started,Monitor port : " + port);
    }
}

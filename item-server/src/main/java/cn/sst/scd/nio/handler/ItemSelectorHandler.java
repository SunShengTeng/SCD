package cn.sst.scd.nio.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author shengtengsun
 * @Description 商品服务的 Handler Of Selector 守护进程
 * @Date 2020/12/1 下午2:41
 * @Version 1.1.0
 **/
public class ItemSelectorHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ItemSelectorHandler.class);
    /**
     * Selector
     **/
    private Selector serverSelector;
    /**
     * The switch is terminated
     **/
    private volatile Boolean stopFlag = false;


    public ItemSelectorHandler(Selector serverSelector) {
        this.serverSelector = serverSelector;
    }

    public void stopMonitor() {
        this.stopFlag = true;
    }

    @Override
    public void run() {

        while (!stopFlag) {
            try {
                this.serverSelector.select(1000);
                Set<SelectionKey> selectionKeys = this.serverSelector.selectedKeys();
                Iterator<SelectionKey> keyIter = selectionKeys.iterator();
                while (keyIter.hasNext()) {
                    SelectionKey key = keyIter.next();
                    keyIter.remove();

                    if (key.isValid()) {
                        // 建立连接
                        if (key.isAcceptable()) {
                            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                            SocketChannel socketChannel = ssc.accept();
                            // 配置为非阻塞式
                            socketChannel.configureBlocking(false);
                            socketChannel.register(this.serverSelector, SelectionKey.OP_READ);
                            logger.info(" Register new SocketChannel And Monitor Read");
                        }
                        // 发送查询请求
                        if (key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
                            int countOfRead = socketChannel.read(byteBuffer);
                            if (countOfRead > 0) {
                                // 读到数据
                                byteBuffer.flip();
                                byte[] bytes = new byte[byteBuffer.remaining()];
                                String dataOfRead = new String(bytes, "UTF-8");
                                // 会写结果
                                doWriter(socketChannel, dataOfRead);
                            } else if (countOfRead < 0) {
                                // 读结束
                                key.cancel();
                                socketChannel.close();
                            } else {

                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doWriter(SocketChannel socketChannel, String dataOfRead) throws IOException {
        JSONObject jsonObject = JSON.parseObject(dataOfRead);
        Map param = jsonObject;
        logger.info("ItemDemon.doWriter() -> 读到的数据:{}", param.toString());

        Integer itemId = Integer.valueOf(String.valueOf(param.get("itemId")));

        HashMap<String, Integer> resultMap = new HashMap<>(16);
        resultMap.put("itemId", itemId);
        resultMap.put("inventory", 100 + itemId);

        ByteBuffer writeBuffer = ByteBuffer.allocate(1000);
        String resultStr = JSONObject.toJSONString(resultMap);
        writeBuffer.put(resultStr.getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

    }
}

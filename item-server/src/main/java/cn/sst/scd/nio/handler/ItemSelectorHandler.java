package cn.sst.scd.nio.handler;

import cn.sst.scd.nio.selector.ItemSelector;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author shengtengsun
 * @Description 库存服务的 Handler Of Selector
 * @Date 2020/12/1 下午4:35
 * @Version 1.1.0
 **/
public class ItemSelectorHandler implements Runnable {

    private volatile boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            Selector selector = ItemSelector.getInstance();
            try {
                // 每隔一秒钟重新获取一遍可被激活的Channel
                selector.select(1000);

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIter = selectionKeys.iterator();

                while (keyIter.hasNext()) {
                    SelectionKey key = keyIter.next();
                    // 从Selection控制位读取信息
                    if (key.isValid()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        // 连接就绪(TCP 握手成功)
                        if (key.isConnectable()) {
                            if (socketChannel.finishConnect()) {
                                socketChannel.register(selector, SelectionKey.OP_READ);

                                // 发送请求
                                HashMap<String, Integer> itemMap = new HashMap<>(16);
                                itemMap.put("itemId", 11);
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
                                byteBuffer.put(JSON.toJSONString(itemMap).getBytes());
                                byteBuffer.flip();
                                socketChannel.write(byteBuffer);

                            }
                        }
                        // 读取服务器响应
                        if (key.isReadable()) {
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
                            int read = socketChannel.read(byteBuffer);
                            if (read > 0) {
                                byteBuffer.flip();
                                byte[] bytes = new byte[byteBuffer.remaining()];
                                byteBuffer.get(bytes);
                                System.out.println("查询到的库存信息" + new String(bytes, "UTF-8"));
                            } else if (read < 0) {
                                socketChannel.close();
                                key.cancel();
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
}

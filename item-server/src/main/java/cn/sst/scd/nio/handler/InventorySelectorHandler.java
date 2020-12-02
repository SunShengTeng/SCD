package cn.sst.scd.nio.handler;

import cn.sst.scd.nio.selector.InventorySelector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author shengtengsun
 * @Description 库存服务的 Handler Of Selector
 * @Date 2020/12/1 下午4:35
 * @Version 1.1.0
 **/
public class InventorySelectorHandler implements Runnable {

    private volatile boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            Selector selector = InventorySelector.getInstance();
            try {
                // 每隔一秒钟重新获取一遍可被激活的Channel
                selector.select(1000);

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIter = selectionKeys.iterator();

                while (keyIter.hasNext()) {
                    SelectionKey key = keyIter.next();

                    if (key.isValid()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        // TCP 握手成功
                        if (key.isConnectable()) {

                            if (socketChannel.finishConnect()) {
                                socketChannel.register(selector, SelectionKey.OP_READ);
                            }
                        }
                        // 读取服务器响应
                        if (key.isReadable()) {
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
                            int read = socketChannel.read(byteBuffer);


                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

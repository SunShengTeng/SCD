package cn.sst.scd.nio.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * @author shengtengsun
 * @Description 库存服务的选择器
 * @Date 2020/12/1 下午4:41
 * @Version 1.1.0
 **/
public class InventorySelector extends Selector {

    private static final Logger logger = LoggerFactory.getLogger(InventorySelector.class);

    static class InventorySelectorInstance {
        private static InventorySelector instance;

        static {
            try {
                instance = (InventorySelector) InventorySelector.open();
            } catch (IOException e) {
                throw new RuntimeException(" init selector for ItemClient fail ! ");
            }
        }
    }

    public static Selector getInstance() {
        return InventorySelectorInstance.instance;
    }

    private InventorySelector() {
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public SelectorProvider provider() {
        return null;
    }

    @Override
    public Set<SelectionKey> keys() {
        return null;
    }

    @Override
    public Set<SelectionKey> selectedKeys() {
        return null;
    }

    @Override
    public int selectNow() throws IOException {
        return 0;
    }

    @Override
    public int select(long timeout) throws IOException {
        return 0;
    }

    @Override
    public int select() throws IOException {
        return 0;
    }

    @Override
    public Selector wakeup() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}

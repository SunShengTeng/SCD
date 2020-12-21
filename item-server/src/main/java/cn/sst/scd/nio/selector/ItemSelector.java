package cn.sst.scd.nio.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @author shengtengsun
 * @Description 库存服务的选择器
 * @Date 2020/12/1 下午4:41
 * @Version 1.1.0
 **/
public class ItemSelector {

    private static final Logger logger = LoggerFactory.getLogger(ItemSelector.class);

    private static class InventorySelectorInstance {
        private static Selector instance;

        static {
            try {
                instance = Selector.open();
            } catch (IOException e) {
                throw new RuntimeException(" init selector for ItemClient fail ! ");
            }
        }
    }

    public static Selector getInstance() {
        return InventorySelectorInstance.instance;
    }

    private ItemSelector() {
    }
}

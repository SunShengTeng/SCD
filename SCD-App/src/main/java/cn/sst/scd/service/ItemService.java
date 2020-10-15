package cn.sst.scd.service;

import java.util.List;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/9 9:17 上午
 * @Version 1.1.0
 **/
public interface ItemService {

    String concurrentForItem(String itemId) throws Exception;

    List<String> findItemList(Object... obs) throws Exception;

    List<String> itemList();

    void addItem(String itemName, Long itemCount);
}

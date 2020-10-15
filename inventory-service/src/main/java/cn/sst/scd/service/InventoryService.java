package cn.sst.scd.service;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/13 2:20 下午
 * @Version 1.1.0
 **/
public interface InventoryService {

    Long getInventoryByItemId(String itemId);

    void addInventoryForItem(Long itemId, Long count);
}

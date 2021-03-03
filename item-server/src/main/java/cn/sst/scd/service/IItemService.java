package cn.sst.scd.service;

import cn.sst.scd.entity.Item;

/**
 * @author shengtengsun
 * @Description
 * @Date 2021/2/3 下午4:01
 * @Version 1.1.0
 **/
public interface IItemService {
    /**
     * 插入商品
     *
     * @param name:
     * @return cn.sst.scd.entity.Item
     * @author shengtengsun
     * @date 2021/3/3 下午2:00
     **/
    Item insertItem(String name);

    void updateItem(Item item);
}

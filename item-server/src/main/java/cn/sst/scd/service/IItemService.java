package cn.sst.scd.service;

import cn.sst.scd.entity.Item;

import java.util.List;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/13 2:58 下午
 * @Version 1.1.0
 **/
public interface IItemService {
    /**
     * @param itemName
     * @return void
     * @author shengtengsun
     * @date 2020/10/13 3:32 下午
     **/
    void addItem(String itemName);

    /**
     * 查询商品列表
     *
     * @param
     * @return java.util.List<cn.sst.scd.entity.Item>
     * @author shengtengsun
     * @date 2020/10/23 5:20 下午
     **/
    List<Item> listItem();
}

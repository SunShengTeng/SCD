package cn.sst.scd.service;

import cn.sst.scd.entity.Item;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过商品ID获取商品名称
     *
     * @param itemId:
     * @return java.util.Map
     * @author shengtengsun
     * @date 2020/12/1 下午4:24
     **/
    Map getItemNameById(String itemId);

    /**
     * 通过商品ID获取商品库存(Nio客户端)
     *
     * @param itemId:
     * @return java.util.Map
     * @author shengtengsun
     * @date 2020/12/1 下午4:25
     **/
    Map getInventoryOfItemByItemId(String itemId);
}

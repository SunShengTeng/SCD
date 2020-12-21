package cn.sst.scd.controller;

import cn.sst.scd.config.ErpHttpConfig;
import cn.sst.scd.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/9/30 11:13 上午
 * @Version 1.1.0
 **/
@RestController
@RequestMapping("/item")
@EnableConfigurationProperties(ErpHttpConfig.class)
public class ItemController {
    @Autowired
    private IItemService itemService;

    @Autowired
    private ErpHttpConfig erpHttpConfig;


    @GetMapping("/info")
    public Map getItemNameById(@RequestParam String itemId) {
        return itemService.getItemNameById(itemId);
    }

    @GetMapping("/inventory/{itemId}")
    public Map getInventoryOfItemByItemId(@PathVariable("itemId") Integer itemId) {
        return itemService.getInventoryOfItemByItemId(itemId);
    }

    @GetMapping("/list")
    public List<String> getItemList() {
        return Arrays.asList("商品1", "商品2", "商品3");
    }

    @PostMapping("/add")
    public void addItem(@RequestParam String itemName) {
        itemService.addItem(itemName);
    }

    /**
     * 模拟禁用商品
     *
     * @param itemId:
     * @return void
     * @author shengtengsun
     * @date 2020/12/16 上午10:42
     **/
    @PostMapping("/disable")
    public void disableItem(@RequestBody Long itemId) {
        itemService.disableItem(itemId);
    }
}

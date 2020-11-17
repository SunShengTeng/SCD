package cn.sst.scd.controller;

import cn.sst.scd.config.ErpHttpConfig;
import cn.sst.scd.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
    public String getItemNameById(@RequestParam String itemId) {
        System.out.println(erpHttpConfig.toString());
        return "商品" + itemId;
    }

    @GetMapping("/list")
    public List<String> getItemList() {
        return Arrays.asList("商品1", "商品2", "商品3");
    }

    @PostMapping("/add")
    public void addItem(@RequestParam String itemName) {
        itemService.addItem(itemName);
    }
}

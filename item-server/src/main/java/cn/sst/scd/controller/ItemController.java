package cn.sst.scd.controller;

import cn.sst.scd.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/info")
    public String getItemNameById(@RequestParam String itemId) {
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

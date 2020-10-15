package cn.sst.scd.controller;

import cn.sst.scd.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author shengtengsun
 * @Description 库存Controller
 * @Date 2020/10/9 4:08 下午
 * @Version 1.1.0
 **/
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/count")
    public Long getInventoryByItemId(@RequestParam String itemId) {
        return inventoryService.getInventoryByItemId(itemId);
    }

    @PostMapping("/add")
    public void addInventoryForItem(@RequestParam Long itemId, @RequestParam Long count) {
        inventoryService.addInventoryForItem(itemId, count);
    }
}

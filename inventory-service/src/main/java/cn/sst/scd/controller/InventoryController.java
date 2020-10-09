package cn.sst.scd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shengtengsun
 * @Description 库存Controller
 * @Date 2020/10/9 4:08 下午
 * @Version 1.1.0
 **/
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @GetMapping("/count")
    public Integer getInventoryByItemId(@RequestParam String itemId) {
        return 100;
    }
}

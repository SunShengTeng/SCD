package cn.sst.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/9/30 11:13 上午
 * @Version 1.1.0
 **/
@RestController
@RequestMapping("/item")
public class ItemController {
    @GetMapping("/info")
    public String getItemNameById(@RequestParam String itemId) {
        return "商品" + itemId;
    }
}

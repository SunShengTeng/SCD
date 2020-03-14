package cn.sst.scd.item.info;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/3/14 2:49 下午
 * @Version 1.1.0
 **/
@RestController
@RequestMapping("/item")
public class ItemInfoController {
    @GetMapping("/info")
    public String itemInfo() {
        return "商品信息";
    }
}

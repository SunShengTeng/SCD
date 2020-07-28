package cn.sst.controller.item;

import cn.sst.annotation.LoggerRequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/7/28 11:17 上午
 * @Version 1.1.0
 **/
@RestController
@RequestMapping("/item")
public class ItemController {

    @GetMapping("/info")
    @LoggerRequestParam
    public String getItemInfo(HttpServletRequest request, @RequestParam("itemId") String itemId) {
        System.out.println("ID为:" + itemId + "商品信息");
        return "商品信息" + itemId;
    }
}

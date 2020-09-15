package cn.sst.controller.item;

import cn.sst.annotation.LoggerRequestParam;
import cn.sst.config.AccountConfig;
import cn.sst.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/7/28 11:17 上午
 * @Version 1.1.0
 **/
@RestController
@RequestMapping("/item")
@EnableConfigurationProperties(value = AccountConfig.class)
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private AccountConfig accountConfig;

    @GetMapping("/info")
    @LoggerRequestParam
    public String getItemInfo(HttpServletRequest request, @RequestParam("itemId") String itemId) {
        System.out.println("ID为:" + itemId + "商品信息" + accountConfig.getNumber());
        return "商品信息" + itemId;
    }

    @GetMapping("/list")
    public List<String> getItemList(@RequestParam("itemType") String itemType) {
        List<String> list = itemService.findItemList(itemType);
        return list;
    }
}

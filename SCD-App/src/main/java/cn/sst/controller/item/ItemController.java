package cn.sst.controller.item;

import cn.sst.service.ItemServiceImpl;
import cn.sst.util.ThreadPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/7/28 11:17 上午
 * @Version 1.1.0
 **/
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;


    @GetMapping("/condition/info")
    public String getItemInfoByItemId(HttpServletRequest request, @RequestParam("itemId") String itemId) {

        System.out.println("ID为:" + itemId);
        System.out.println("当前线程:" + Thread.currentThread().getName());
        System.out.println("是否守护线程：" + Thread.currentThread().isDaemon());
        System.out.println("当前时间" + LocalDateTime.now().toString());
        return "商品信息" + itemId;
    }


    @GetMapping("/info")
    public String getItemInfo() {
        return "商品信息";
    }


    @GetMapping("/concurrent")
    public String concurrent() throws Exception {
        System.out.println("当次请求开始！");
        ThreadPoolExecutor executor = ThreadPoolUtil.itemServiceThreadPoolExecutor();
        for (int i = 0; i < 0x5; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    itemService.concurrentForItem(String.valueOf(finalI));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("当次请求结束！");
        return "item";
    }

    @GetMapping("/list")
    // @PreAuthorize("") // TODO open Authorization
    public List<String> getItemList(@RequestParam("itemType") String itemType) throws Exception {
        List<String> list = itemService.findItemList(itemType);
        return list;
    }
}

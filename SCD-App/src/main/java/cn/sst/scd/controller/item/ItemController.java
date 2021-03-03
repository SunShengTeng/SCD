package cn.sst.scd.controller.item;

import cn.sst.scd.domain.vo.Admin;
import cn.sst.scd.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

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
    private ItemService itemService;


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
        int nextInt = new Random().nextInt(10);
        System.out.println("当次请求开始！" + nextInt);

        /*
        ArrayList<String> itemList = new ArrayList<>();
        ThreadPoolExecutor executor = ThreadPoolUtil.itemServiceThreadPoolExecutor();
        for (int i = 0; i < 0x5; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    String item = itemService.concurrentForItem(String.valueOf(finalI));
                    itemList.add(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }*/
        String item = itemService.concurrentForItem(String.valueOf(nextInt));
        System.out.println("当次请求结束！" + nextInt);
        return item;
    }

    @GetMapping("/list")
    public List<String> getItemList() {
        List<String> list = itemService.itemList();
        return list;
    }

    @GetMapping("/find")
    public List<String> findItemList() throws Exception {

        Admin admin = new Admin();
        admin.setId("123");
        admin.setName("name");
        admin.setIsDel(1);
        return itemService.findItemList(admin);
    }

    /**
     * 新增商品
     *
     * @param itemName, itemCount
     * @return void
     * @author shengtengsun
     * @date 2020/10/14 9:28 上午
     **/
    @GetMapping("/add/{name}/{count}")
    public void addItem(@PathVariable(name = "name") String itemName, @PathVariable(name = "count") Long itemCount) {
        itemService.addItem(itemName, itemCount);
    }
}

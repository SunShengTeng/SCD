package cn.sst.service.impl;

import cn.sst.annotation.EnableOperatorLog;
import cn.sst.enums.SystemOperatorType;
import cn.sst.feign.InventoryServiceFeignClient;
import cn.sst.feign.ItemServiceFeignClient;
import cn.sst.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Subscriber;

import java.util.Arrays;
import java.util.List;

/**
 * @author shengtengsun
 * @Description 商品服务
 * @Date 2020/7/28 4:24 下午
 * @Version 1.1.0
 **/
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemServiceFeignClient itemServiceFeignClient;
    @Autowired
    private InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    @EnableOperatorLog(operatorType = SystemOperatorType.INSERT, priModel = "模块1")
    public List<String> findItemList(Object... objects) throws Exception {
        return Arrays.asList(objects + "商品1", objects + "商品2");
    }

    @Override
    public List<String> itemList() {
        return itemServiceFeignClient.getItemList();
    }

    public Subscriber subscribeInventory() {
        return new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("当前批次的库存增加完成");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("消费库存：当前增加的库存数量为" + integer);
            }
        };
    }

    @Override
    // @EnableOperatorLog(operatorType = SystemOperatorType.INSERT, priModel = "123")
    public String concurrentForItem(String itemId) throws Exception {
        String itemInfo = itemServiceFeignClient.getItemNameById(itemId);
        System.out.println("商品信息" + itemInfo);

        Integer inventory = inventoryServiceFeignClient.getInventoryByItemId(itemId);
        System.out.println("库存数量" + inventory);
        return itemInfo;
    }
}

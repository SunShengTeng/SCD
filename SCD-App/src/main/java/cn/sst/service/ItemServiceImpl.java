package cn.sst.service;

import cn.sst.annotation.EnableOperatorLog;
import cn.sst.enums.SystemOperatorType;
import cn.sst.feign.ItemServiceFeignService;
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
    private ItemServiceFeignService itemServiceFeignService;

    //@HystrixCommand
    @EnableOperatorLog(operatorType = SystemOperatorType.INSERT, priModel = "模块1")
    public List<String> findItemList(String itemType) throws Exception {
        //
        try {
            String str = null;
            System.out.println(str.substring(0, 2));
        } catch (Exception e) {
            throw e;
        }
        return Arrays.asList(itemType + "商品1", itemType + "商品2");
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

    @EnableOperatorLog(operatorType = SystemOperatorType.INSERT, priModel = "123")
    public String concurrentForItem(String itemId) throws Exception {

        String itemInfo = itemServiceFeignService.getItemNameById(itemId);

        System.out.println("主线程的名字" + Thread.currentThread().getName() + "其他信息" + Thread.currentThread().toString());
        return itemInfo;
    }
}

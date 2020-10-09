package cn.sst.feign.callback;

import cn.sst.feign.ItemServiceFeignClient;

import java.util.Collections;
import java.util.List;

/**
 * @author shengtengsun
 * @Description 商品服务的降级策略
 * @Date 2020/9/30 1:50 下午
 * @Version 1.1.0
 **/
public class ItemServiceFallBack implements ItemServiceFeignClient {
    @Override
    public String getItemNameById(String itemId) {
        // TODO 降级
        System.out.println("服务降级策略");
        return "默认商品";
    }

    @Override
    public List<String> getItemList() {
        return Collections.emptyList();
    }
}

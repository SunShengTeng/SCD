package cn.sst.scd.feign.callback;

import cn.sst.scd.feign.InventoryServiceFeignClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shengtengsun
 * @Description 库存中心服务降级
 * @Date 2020/10/9 4:15 下午
 * @Version 1.1.0
 **/
@Slf4j
public class InventoryServiceTimeOutFallBack implements InventoryServiceFeignClient {

    @Override
    public Long getInventoryByItemId(String itemId) {
        log.error("返回预定义库存数量");
        return 0L;
    }

    @Override
    public void addInventoryForItem(Long itemId, Long count) {
        System.out.println("添加商品服务降级");
        throw new IllegalArgumentException("AAA");
    }
}

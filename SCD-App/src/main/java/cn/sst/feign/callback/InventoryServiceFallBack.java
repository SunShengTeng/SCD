package cn.sst.feign.callback;

import cn.sst.feign.InventoryServiceFeignClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/9 4:15 下午
 * @Version 1.1.0
 **/
@Slf4j
public class InventoryServiceFallBack implements InventoryServiceFeignClient {
    @Override
    public Integer getInventoryByItemId(String itemId) {
        log.error("返回预定义库存数量");
        return 0;
    }
}

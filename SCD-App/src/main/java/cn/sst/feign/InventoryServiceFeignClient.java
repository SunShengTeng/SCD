package cn.sst.feign;

import cn.sst.feign.factory.InventoryServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/9 4:14 下午
 * @Version 1.1.0
 **/
@FeignClient(value = "inventory-service", fallbackFactory = InventoryServiceFallBackFactory.class)
public interface InventoryServiceFeignClient {
    /**
     * 获取商品库存总数量
     *
     * @param itemId
     * @return java.lang.Integer
     * @author shengtengsun
     * @date 2020/10/9 4:16 下午
     **/
    @GetMapping("/inventory/count")
    Integer getInventoryByItemId(@RequestParam String itemId);
}

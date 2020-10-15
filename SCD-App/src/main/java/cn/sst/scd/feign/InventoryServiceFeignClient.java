package cn.sst.scd.feign;

import cn.sst.scd.feign.factory.InventoryServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    Long getInventoryByItemId(@RequestParam String itemId);

    /**
     * 添加库存
     *
     * @param itemId
     * @param count
     * @return void
     * @author shengtengsun
     * @date 2020/10/13 3:24 下午
     **/
    @PostMapping("/inventory/add")
    void addInventoryForItem(@RequestParam Long itemId, @RequestParam Long count);
}

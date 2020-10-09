package cn.sst.feign.factory;

import cn.sst.feign.InventoryServiceFeignClient;
import cn.sst.feign.callback.InventoryServiceFallBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/9 4:17 下午
 * @Version 1.1.0
 **/
@Component
public class InventoryServiceFallBackFactory implements FallbackFactory<InventoryServiceFeignClient> {
    @Override
    public InventoryServiceFeignClient create(Throwable cause) {
        InventoryServiceFallBack fallBack = new InventoryServiceFallBack();
        return fallBack;
    }
}

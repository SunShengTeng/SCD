package cn.sst.scd.feign.factory;

import cn.sst.scd.feign.InventoryServiceFeignClient;
import cn.sst.scd.feign.callback.InventoryServiceTimeOutFallBack;
import com.netflix.hystrix.exception.HystrixTimeoutException;
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
        if (cause instanceof HystrixTimeoutException) {
            return new InventoryServiceTimeOutFallBack();
        }
        return new InventoryServiceFeignClient() {
            @Override
            public Long getInventoryByItemId(String itemId) {
                throw new RuntimeException(cause.getCause() != null ? cause.getCause().getMessage() : cause.getMessage());
            }

            @Override
            public void addInventoryForItem(Long itemId, Long count) {

            }
        };
    }
}

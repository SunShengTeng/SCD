package cn.sst.scd.feign.factory;

import cn.sst.scd.feign.ItemServiceFeignClient;
import cn.sst.scd.feign.callback.ItemServiceTimeOutFallBack;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/9 3:05 下午
 * @Version 1.1.0
 **/
@Component
public class ItemServiceFallBackFactory implements FallbackFactory<ItemServiceFeignClient> {
    @Override
    public ItemServiceFeignClient create(Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return new ItemServiceTimeOutFallBack();
        }
        return null;
    }
}

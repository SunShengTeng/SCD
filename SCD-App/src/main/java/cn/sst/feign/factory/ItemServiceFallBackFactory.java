package cn.sst.feign.factory;

import cn.sst.feign.ItemServiceFeignClient;
import cn.sst.feign.callback.ItemServiceFallBack;
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
        ItemServiceFallBack fallBack = new ItemServiceFallBack();
        return fallBack;
    }
}

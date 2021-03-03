package cn.sst.scd.feign.factory;

import cn.sst.scd.feign.CrmServiceFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author shengtengsun
 * @Description CRM Feign Factory
 * @Date 2020/11/18 上午11:30
 * @Version 1.1.0
 **/
@Component
@Slf4j
public class CrmServiceFallBackFactory implements FallbackFactory<CrmServiceFeignClient> {
    @Override
    public CrmServiceFeignClient create(Throwable cause) {
        return null;
    }
}

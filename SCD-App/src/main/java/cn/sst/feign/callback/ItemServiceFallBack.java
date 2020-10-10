package cn.sst.feign.callback;

import cn.sst.feign.ItemServiceFeignClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * @author shengtengsun
 * @Description 商品服务的降级策略
 * @Date 2020/9/30 1:50 下午
 * @Version 1.1.0
 **/
@Slf4j
public class ItemServiceFallBack implements ItemServiceFeignClient {
    @Override
    public String getItemNameById(String itemId) {
        // TODO 降级
        log.error("商品服务FallBack");
        return "默认商品";
    }

    @Override
    public List<String> getItemList() {
        return Collections.emptyList();
    }
}

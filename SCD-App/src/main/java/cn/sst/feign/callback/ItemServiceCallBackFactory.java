package cn.sst.feign.callback;

import cn.sst.feign.ItemServiceFeignService;

/**
 * @author shengtengsun
 * @Description 商品服务的降级策略
 * @Date 2020/9/30 1:50 下午
 * @Version 1.1.0
 **/
public class ItemServiceCallBackFactory implements ItemServiceFeignService {
    @Override
    public String getItemNameById(String itemId) {
        // TODO 降级
        return null;
    }
}

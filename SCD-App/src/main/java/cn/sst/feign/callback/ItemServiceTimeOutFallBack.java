package cn.sst.feign.callback;

import cn.sst.feign.ItemServiceFeignClient;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author shengtengsun
 * @Description 商品服务的降级策略
 * @Date 2020/9/30 1:50 下午
 * @Version 1.1.0
 **/
@Slf4j
public class ItemServiceTimeOutFallBack implements ItemServiceFeignClient {

    @Override
    public String getItemNameById(String itemId) {
        log.error("商品服务-超时-FallBack");
        return "默认商品";
    }

    @Override
    public List<String> getItemList() {
        throw new ItemServiceTimeOutException("getItemList");
    }

    public class ItemServiceTimeOutException extends RuntimeException {
        public ItemServiceTimeOutException(String methodName) {
            super("ItemService服务" + methodName + "()请求超时");
        }
    }
}

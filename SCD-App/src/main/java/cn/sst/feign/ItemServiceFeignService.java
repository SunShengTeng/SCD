package cn.sst.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/9/30 11:20 上午
 * @Version 1.1.0
 **/
@FeignClient(value = "item-service")
public interface ItemServiceFeignService {
    /**
     * 商品详细信息
     *
     * @param itemId
     * @return java.lang.String
     * @author shengtengsun
     * @date 2020/10/9 8:44 上午
     **/
    @GetMapping("/item/info")
    String getItemNameById(@RequestParam String itemId);
}

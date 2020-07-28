package cn.sst.service.item;

import cn.sst.annotation.UsedDateSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author shengtengsun
 * @Description 商品服务
 * @Date 2020/7/28 4:24 下午
 * @Version 1.1.0
 **/
@Service
public class ItemService {

    @UsedDateSource("booking")
    public List<String> findItemList(String itemType) {
        return Arrays.asList(itemType + "商品1", itemType + "商品2");
    }
}

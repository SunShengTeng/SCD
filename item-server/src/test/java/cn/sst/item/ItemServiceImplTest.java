package cn.sst.item;

import cn.sst.scd.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/13 3:37 下午
 * @Version 1.1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemServiceImplTest {
    @Autowired
    private ItemService itemService;

    @Test
    public void testAddItem() {
        System.out.println("3");
        // itemService.addItem("商品3");
    }
}

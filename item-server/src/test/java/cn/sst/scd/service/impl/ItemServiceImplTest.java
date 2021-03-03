package cn.sst.scd.service.impl;

import cn.sst.scd.entity.Item;
import cn.sst.scd.service.IItemInfoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ItemServiceImpl Tester.
 *
 * @author sunshengteng
 * @version 1.0
 * @since <pre>10月 27, 2020</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceImplTest {

    @Autowired
    private IItemInfoService iItemService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addItem(String itemName)
     */
    @Test
    public void testAddItem() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: listItem()
     */
    @Test
    public void testListItem() throws Exception {
        List<Item> items = iItemService.listItem();
        System.out.println(items);
    }


}

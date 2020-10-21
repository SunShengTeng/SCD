package cn.sst.scd;

import cn.sst.scd.exception.StorageException;
import cn.sst.scd.service.InventoryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * InventoryServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10月 13, 2020</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceImplTest {
    @Autowired
    private InventoryService inventoryService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getInventoryByItemId(String itemId)
     */

    public void testGetInventoryByItemId() throws Exception {
        Long inventory = inventoryService.getInventoryByItemId("1");
        System.out.println(inventory);
    }

    @Test
    public void addInventory() throws StorageException {
        inventoryService.addInventoryForItem(99L, 100L);
    }


} 

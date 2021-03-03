package cn.sst.scd.service.impl;

import cn.sst.scd.annotation.UsedDateSource;
import cn.sst.scd.datasource.DataSourceType;
import cn.sst.scd.entity.Item;
import cn.sst.scd.entity.ItemInfo;
import cn.sst.scd.exception.ItemException;
import cn.sst.scd.mapper.ItemInfoMapper;
import cn.sst.scd.nio.selector.ItemSelector;
import cn.sst.scd.service.IItemInfoService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/13 2:58 下午
 * @Version 1.1.0
 **/
@Service
public class ItemInfoServiceImpl implements IItemInfoService {
    @Autowired
    private ItemInfoMapper itemInfoMapper;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private IItemInfoService iItemService;


    @Override
    @UsedDateSource(value = DataSourceType.item)
    @Transactional(rollbackFor = Exception.class)
    public void addItem(String itemName) throws ItemException {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setName(itemName);
        itemInfoMapper.insert(itemInfo);

        /*try {
            iItemService.copyItem(itemName + "的附加商品");
        } catch (Exception e) {
            AbstractTransactionAspect.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }*/

    }


    @Override
    @UsedDateSource(value = DataSourceType.item)
    public List<Item> listItem() {
        /*String sql = "select " +
                "t.id as id" +
                ", t.name as name" +
                ", d.describe as `describe`" +
                ", d.create_time as createTime" +
                " from item_info t" +
                " join item_detail d" +
                " on t.id = d.item_id;";
        Query query = entityManager.createNativeQuery(sql, Item.class);
        NativeQuery nativeQuery = query.unwrap(NativeQuery.class);
        List resultList1 = nativeQuery.getResultList();

        nativeQuery.setResultTransformer(Transformers.aliasToBean(Item.class));
        List resultList = nativeQuery.getResultList();
        return resultList;*/
        return null;
    }

    @Override
    public Map getItemNameById(String itemId) {
        return null;
    }

    @Override
    public Map getInventoryOfItemByItemId(Integer itemId) {
        HashMap<String, Integer> itemMap = new HashMap<>(16);
        itemMap.put("itemId", itemId);

        // 查询库存的Selector
        Selector selector = ItemSelector.getInstance();

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            if (socketChannel.connect(new InetSocketAddress("127.0.0.1", 8090))) {
                // 发送请求
                ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
                byteBuffer.put(JSON.toJSONString(itemMap).getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                // 注册读，获取库存信息
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void disableItem(Long itemId) {

    }

    @UsedDateSource(value = DataSourceType.item)
    @Transactional
    @Override
    public void copyItem(String itemName) throws ItemException {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setName(itemName);

        try {
            itemInfoMapper.insert(itemInfo);
            System.out.println(1 / 0);
        } catch (Exception e) {
            throw new ItemException();
            /*throw new RuntimeException();*/
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void transaction() {
        List<String> asList = Arrays.asList("AA");
        asList.forEach(System.out::println);
    }
}

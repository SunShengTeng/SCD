package cn.sst.scd.service.impl;

import cn.sst.scd.annotation.UsedDateSource;
import cn.sst.scd.datasource.DataSourceType;
import cn.sst.scd.entity.Item;
import cn.sst.scd.entity.ItemInfo;
import cn.sst.scd.mapper.ItemInfoMapper;
import cn.sst.scd.service.IItemService;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/13 2:58 下午
 * @Version 1.1.0
 **/
@Service
public class ItemServiceImpl implements IItemService {
    @Autowired
    private ItemInfoMapper itemInfoMapper;
    @Autowired
    private EntityManager entityManager;


    @Override
    @UsedDateSource(value = DataSourceType.item)
    @Transactional(rollbackFor = Exception.class)
    public void addItem(String itemName) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setName(itemName);

        int insert = itemInfoMapper.insert(itemInfo);
    }

    @Override
    @UsedDateSource(value = DataSourceType.item)
    public List<Item> listItem() {
        String sql = "select " +
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
        return resultList;
    }

}

package cn.sst.scd.service.impl;

import cn.sst.scd.annotation.UsedDateSource;
import cn.sst.scd.datasource.DataSourceType;
import cn.sst.scd.entity.ItemInfo;
import cn.sst.scd.mapper.ItemInfoMapper;
import cn.sst.scd.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/13 2:58 下午
 * @Version 1.1.0
 **/
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemInfoMapper itemInfoMapper;


    @Override
    @UsedDateSource(value = DataSourceType.item)
    @Transactional(rollbackFor = Exception.class)
    public void addItem(String itemName) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setName(itemName);

        int insert = itemInfoMapper.insert(itemInfo);
    }

}

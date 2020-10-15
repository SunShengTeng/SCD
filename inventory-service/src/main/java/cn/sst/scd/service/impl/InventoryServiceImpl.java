package cn.sst.scd.service.impl;

import cn.sst.scd.dao.BatchInfoMapper;
import cn.sst.scd.dao.StorageMapper;
import cn.sst.scd.entity.Storage;
import cn.sst.scd.entity.StorageExample;
import cn.sst.scd.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/13 2:21 下午
 * @Version 1.1.0
 **/
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private StorageMapper storageMapper;

    @Autowired
    private BatchInfoMapper batchInfoMapper;

    @Override
    public Long getInventoryByItemId(String itemId) {
        StorageExample storageExample = new StorageExample();
        StorageExample.Criteria criteria = storageExample.createCriteria();
        criteria.andItemIdEqualTo(Long.valueOf(itemId));

        List<Storage> storages = storageMapper.selectByExample(storageExample);
        if (!CollectionUtils.isEmpty(storages)) {
            return storages.get(0).getCount();
        }
        return 0L;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addInventoryForItem(Long itemId, Long count) {
        Storage storage = new Storage();
        storage.setItemId(itemId);
        storage.setCount(count);
        storageMapper.insert(storage);

        System.out.println(1 / 0);

        /*BatchInfo batchInfo = new BatchInfo();
        batchInfo.setBatchNumber(String.valueOf(new Random().nextInt(10)));
        batchInfoMapper.insert(batchInfo);*/
    }
}

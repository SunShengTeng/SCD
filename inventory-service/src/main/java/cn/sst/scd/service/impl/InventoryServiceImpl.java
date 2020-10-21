package cn.sst.scd.service.impl;

import cn.sst.scd.entity.BatchInfo;
import cn.sst.scd.entity.Storage;
import cn.sst.scd.entity.StorageExample;
import cn.sst.scd.exception.StorageException;
import cn.sst.scd.service.IBatchInfoService;
import cn.sst.scd.service.IStorageService;
import cn.sst.scd.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/13 2:21 下午
 * @Version 1.1.0
 **/
@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private IStorageService storageService;

    @Autowired
    private IBatchInfoService batchInfoService;
    @Autowired
    private StorageServiceWrapper storageServiceWrapper;

    @Override
    public Long getInventoryByItemId(String itemId) {
        StorageExample storageExample = new StorageExample();
        StorageExample.Criteria criteria = storageExample.createCriteria();
        criteria.andItemIdEqualTo(Long.valueOf(itemId));

        List<Storage> storages = storageService.selectByExample(storageExample);
        if (!CollectionUtils.isEmpty(storages)) {
            return storages.get(0).getCount();
        }
        return 0L;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addInventoryForItem(Long itemId, Long count) throws Exception {

        // 1、创建批次
        BatchInfo batchInfo = new BatchInfo();
        batchInfo.setBatchNumber("BT001");
        batchInfoService.createBatchInfo(batchInfo);
        // 2、增加库存
        Storage storage = new Storage();
        storage.setItemId(itemId);
        storage.setCount(count);
        storage.setBatchNum(batchInfo.getBatchNumber());
        try {
            storageServiceWrapper.createStorage(storage);
        } catch (StorageException e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}

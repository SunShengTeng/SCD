package cn.sst.scd.service.impl;

import cn.sst.scd.entity.Storage;
import cn.sst.scd.exception.StorageException;
import cn.sst.scd.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/21 6:58 下午
 * @Version 1.1.0
 **/
@Service
public class StorageServiceWrapper {
    @Autowired
    private IStorageService storageService;

    public void createStorage(Storage storage) throws StorageException {
        storageService.createStorage(storage);
    }
}

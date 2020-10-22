package cn.sst.scd.service;

import cn.sst.scd.entity.Storage;
import cn.sst.scd.entity.StorageExample;
import cn.sst.scd.exception.StorageException;

import java.util.List;

/**
 * @author shengtengsun
 * @Description 库存Interface
 * @Date 2020/10/21 6:49 下午
 * @Version 1.1.0
 **/
public interface IStorageService {
    /**
     * 创建库存
     *
     * @param storage
     * @return void
     * @author shengtengsun
     * @date 2020/10/21 6:50 下午
     **/
    void createStorage(Storage storage) throws StorageException;

    List<Storage> selectByExample(StorageExample storageExample);
}

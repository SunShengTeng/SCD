package cn.sst.scd.service;

import cn.sst.scd.entity.BatchInfo;

/**
 * @author shengtengsun
 * @Description 批次ServiceInterface
 * @Date 2020/10/21 5:13 下午
 * @Version 1.1.0
 **/
public interface IBatchInfoService {
    /**
     * 为商品创建批次
     *
     * @return void
     * @author shengtengsun
     * @date 2020/10/21 5:15 下午
     **/
    void createBatchInfo();

    /**
     * 为商品创建批次
     *
     * @param batchInfo
     * @return void
     * @author shengtengsun
     * @date 2020/10/21 5:29 下午
     **/
    void createBatchInfo(BatchInfo batchInfo);
}

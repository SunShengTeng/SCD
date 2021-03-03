package cn.sst.scd.service.impl;

import cn.sst.scd.dao.BatchInfoMapper;
import cn.sst.scd.entity.BatchInfo;
import cn.sst.scd.service.IBatchInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shengtengsun
 * @Description 批次ServiceImpl
 * @Date 2020/10/21 5:15 下午
 * @Version 1.1.0
 **/
@Service
@Slf4j
public class BatchInfoServiceImpl implements IBatchInfoService {

    @Autowired
    private BatchInfoMapper batchInfoMapper;

    @Override
    public void createBatchInfo() {

        batchInfoMapper.insert(new BatchInfo() {{
            setBatchNumber("BT001");
        }});
    }

    @Override
    public void createBatchInfo(BatchInfo batchInfo) {
        log.info("插入批次");
        batchInfoMapper.insert(batchInfo);
    }
}

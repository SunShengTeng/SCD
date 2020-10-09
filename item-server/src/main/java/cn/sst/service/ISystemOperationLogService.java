package cn.sst.service;


import cn.sst.dto.OperationLogDTO;

/**
 * @author shengtengsun
 * @Description 系统操作Service
 * @Date 2020/9/27 11:29 上午
 * @Version 1.1.0
 **/
public interface ISystemOperationLogService {
    /**
     * 插入操作日志
     *
     * @param operationLoggerDTO
     * @return java.lang.String
     * @author shengtengsun
     * @date 2020/9/27 11:37 上午
     **/
    String insertOperationRecord(OperationLogDTO operationLoggerDTO);
}

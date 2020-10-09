package cn.sst.service.impl;

import cn.sst.dto.OperationLogDTO;
import cn.sst.dto.UserInfoDTO;
import cn.sst.entity.SystemOperateLog;
import cn.sst.mapper.SystemOperateLogRepository;
import cn.sst.service.ISystemOperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/9/27 11:30 上午
 * @Version 1.1.0
 **/
@Service
@Slf4j
public class SystemOperationLogServiceImpl implements ISystemOperationLogService {
    @Autowired
    private SystemOperateLogRepository operateLogRepository;

    @Override
    public String insertOperationRecord(OperationLogDTO dto) {
        SystemOperateLog operateLog = new SystemOperateLog();

        UserInfoDTO user = dto.getUser();
        operateLog.setOperatorName(user.getNickname());
        operateLog.setOperatorNumber(user.getLoginName());
        operateLog.setIpAddress(dto.getIpAddress());
        operateLog.setPriModel(dto.getPriModel());
        operateLog.setSecModel(dto.getSecModel());
        operateLog.setThirdModel(dto.getThirdModel());
        operateLog.setMethodName(dto.getMethodName());
        operateLog.setClassName(dto.getClassName());
        operateLog.setOperatorNote(dto.getOperatorNote());
        operateLog.setOperatorTime(dto.getOperatorTime());
        operateLog.setOperatorType(dto.getOperatorType().getVal());

        operateLog.setProjectId("");
        operateLog.setSuccessFlag(1);
        operateLog.setErrorMsg("");
        try {
            operateLogRepository.save(operateLog);
        } catch (Exception e) {
            log.error("SystemOperationLogServiceImpl.insertOperationRecord:插入操作记录失败！失败原因{}", e.getMessage());
        }
        return operateLog.getOperatorName();
    }
}

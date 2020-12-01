package cn.sst.scd.feign.callback;

import cn.sst.scd.dto.CrmLogisticsPar;
import cn.sst.scd.dto.CrmProviderPromisePar;
import cn.sst.scd.dto.CrmReleasePar;
import cn.sst.scd.dto.CrmReturnDTO;
import cn.sst.scd.feign.CrmServiceFeignClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author shengtengsun
 * @Description CRM 服务降级
 * @Date 2020/11/18 上午11:31
 * @Version 1.1.0
 **/
@Slf4j
public class CrmServiceTimeOutFallBack implements CrmServiceFeignClient {

    @Override
    public CrmReturnDTO getLogisticsTime(Map<String, CrmLogisticsPar> logisticsPar) {
        return null;
    }

    @Override
    public CrmReturnDTO releaseDistributePlan(CrmReleasePar releasePar) {
        return null;
    }

    @Override
    public CrmReturnDTO getProviderPromise(Map<String, CrmProviderPromisePar> providerPromiseParMap) {
        return null;
    }
}

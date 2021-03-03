package cn.sst.scd.feign;

import cn.sst.scd.dto.CrmLogisticsPar;
import cn.sst.scd.dto.CrmProviderPromisePar;
import cn.sst.scd.dto.CrmReleasePar;
import cn.sst.scd.dto.CrmReturnDTO;
import cn.sst.scd.feign.factory.CrmServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author shengtengsun
 * @Description CRM Feign 客户端
 * 接口文档地址: http://10.1.80.102:3011/project/197/interface/api/18395
 * 备注：如果没有权限访问，联系"王磊6"加入项目，电话：16651086906
 * @Date 2020/11/18 上午11:28
 * @Version 1.1.0
 **/
@FeignClient(name = "${http.crm-server.name}", url = "${http.crm-server.host}", fallbackFactory = CrmServiceFallBackFactory.class)
public interface CrmServiceFeignClient {
    /**
     * 获取地区物流时效
     *
     * @param logisticsPar: 物流时效
     * @return cn.sst.scd.dto.CrmReturnDTO
     * @author shengtengsun
     * @date 2020/11/18 下午1:21
     **/
    @PostMapping("/materialRequest/getLogistics")
    CrmReturnDTO getLogisticsTime(@RequestBody Map<String, CrmLogisticsPar> logisticsPar);

    /**
     * 发布要货计划
     *
     * @param releasePar: 要货计划
     * @return cn.sst.scd.dto.CrmReturnDTO
     * @author shengtengsun
     * @date 2020/11/18 下午1:22
     **/
    @PostMapping("/materialRequest/submitBatch")
    CrmReturnDTO releaseDistributePlan(@RequestBody CrmReleasePar releasePar);

    /**
     * 获取物料的供应链承诺数据
     *
     * @param providerPromiseParMap:
     * @return cn.sst.scd.dto.CrmReturnDTO
     * @author shengtengsun
     * @date 2020/11/18 下午6:40
     **/
    @PostMapping("/materialRequest/getListNew")
    CrmReturnDTO getProviderPromise(@RequestBody Map<String, CrmProviderPromisePar> providerPromiseParMap);

}

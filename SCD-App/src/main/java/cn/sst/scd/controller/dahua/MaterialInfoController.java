package cn.sst.scd.controller.dahua;

import cn.sst.scd.annotation.LoggerRequestParam;
import cn.sst.scd.dto.*;
import cn.sst.scd.feign.CrmServiceFeignClient;
import cn.sst.scd.feign.ErpServiceFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/11/16 7:44 下午
 * @Version 1.1.0
 **/
@Slf4j
@RestController
@RequestMapping("/dahua/material")
public class MaterialInfoController {
    @Autowired
    private ErpServiceFeignClient erpServiceFeignClient;
    @Autowired
    private CrmServiceFeignClient crmServiceFeignClient;

    @PostMapping("/erp")
    @LoggerRequestParam
    public void getCanRequiredNum(@RequestBody ErpMaterialPar materialPar) {

        ErpReturnDTO canRequiredNum = erpServiceFeignClient.getCanRequiredNum(materialPar);

        ErpReturnDTO shippedAmount = erpServiceFeignClient.getShippedAmount(materialPar);

        System.out.println(canRequiredNum);
        System.out.println(shippedAmount);
    }

    @GetMapping("/crm")
    @LoggerRequestParam
    public void debugCrmService() {

        HashMap<String, CrmLogisticsPar> logisticsParHashMap = new HashMap<>(16);
        CrmLogisticsPar crmLogisticsPar = new CrmLogisticsPar();
        crmLogisticsPar.setOrigin("浙江省");
        crmLogisticsPar.setCity("绍兴市");
        crmLogisticsPar.setArea("越城区");
        logisticsParHashMap.put("argsMap", crmLogisticsPar);

        CrmReturnDTO logisticsTime = crmServiceFeignClient.getLogisticsTime(logisticsParHashMap);

        CrmReleasePar crmReleasePar = new CrmReleasePar();

        CrmReleasePar.MaterialPar materialPar = CrmReleasePar.MaterialPar.builder()
                .PROJECT_ID("919")
                .PROJECT_NUM("S20119050355")
                .ITEM_NUMBER("1.5.01.98.11070")
                .REQUIRE_TOTAL(BigDecimal.valueOf(200.20))
                .ORDER_QTY(BigDecimal.valueOf(10.12))
                .REQUIRE_QTY(BigDecimal.valueOf(1))
                .REQUIRE_ARRIVAL_TIME("2020-11-29")
                .build();
        crmReleasePar.setUserId(112656L);
        ArrayList<CrmReleasePar.MaterialPar> materialPars = new ArrayList<>();
        materialPars.add(materialPar);
        crmReleasePar.setData(materialPars);

        CrmReturnDTO crmReturnDTO = crmServiceFeignClient.releaseDistributePlan(crmReleasePar);

        System.out.println(logisticsTime);
        System.out.println(crmReturnDTO);
    }

    /**
     * 获取供应链承诺数据
     *
     * @param :
     * @return void
     * @author shengtengsun
     * @date 2020/11/18 下午6:42
     **/
    @GetMapping("/crm/promise")
    public void getProviderPromise() {

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        CrmProviderPromisePar promisePar = new CrmProviderPromisePar();
        promisePar.setProjectNum("S20119050355");
        promisePar.setCurrentTime(pattern.format(LocalDate.now()));
        promisePar.setBefore(10);
        promisePar.setAfter(7);
        promisePar.setMaterialNumArr(Arrays.asList("1.5.01.98.11070"));

        CrmReturnDTO crmReturnDTO = crmServiceFeignClient.getProviderPromise(new HashMap<String, CrmProviderPromisePar>(5) {{
            put("argsMap", promisePar);
        }});
        List<Map> dtoData = (List<Map>) crmReturnDTO.getData();
        HashMap<String, Map> providerPromiseMap = new HashMap<>();
        for (Map map : dtoData) {
            providerPromiseMap.put(String.valueOf(map.get("ITEM_NUMBER")), map);
        }

        ArrayList<Map> result = new ArrayList<>();


        System.out.println(crmReturnDTO);

    }
}

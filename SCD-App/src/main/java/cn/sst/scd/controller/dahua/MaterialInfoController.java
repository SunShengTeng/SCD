package cn.sst.scd.controller.dahua;

import cn.sst.scd.annotation.LoggerRequestParam;
import cn.sst.scd.dto.ErpMaterialPar;
import cn.sst.scd.dto.ErpReturnDTO;
import cn.sst.scd.feign.ErpServiceFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/info")
    @LoggerRequestParam
    public void getCanRequiredNum(@RequestBody ErpMaterialPar materialPar) {

        ErpReturnDTO canRequiredNum = erpServiceFeignClient.getCanRequiredNum(materialPar);

        ErpReturnDTO shippedAmount = erpServiceFeignClient.getShippedAmount(materialPar);

        System.out.println(canRequiredNum);
        System.out.println(shippedAmount);
    }
}

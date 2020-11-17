package cn.sst.scd.feign.callback;

import cn.sst.scd.dto.ErpMaterialPar;
import cn.sst.scd.dto.ErpReturnDTO;
import cn.sst.scd.feign.ErpServiceFeignClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

/**
 * @author shengtengsun
 * @Description ERP 服务降级
 * @Date 2020/11/16 7:32 下午
 * @Version 1.1.0
 **/
@Slf4j
public class ErpServiceTimeOutFallBack implements ErpServiceFeignClient {

    @Override
    public ErpReturnDTO getCanRequiredNum(ErpMaterialPar materialPar) {
        ErpReturnDTO erpReturnDTO = new ErpReturnDTO();
        erpReturnDTO.setCode(2);
        erpReturnDTO.setMsg("请求超时");
        erpReturnDTO.setData(Collections.EMPTY_LIST);
        return erpReturnDTO;
    }

    @Override
    public ErpReturnDTO getShippedAmount(ErpMaterialPar materialPar) {
        ErpReturnDTO erpReturnDTO = new ErpReturnDTO();
        erpReturnDTO.setCode(2);
        erpReturnDTO.setMsg("请求超时");
        erpReturnDTO.setData(Collections.EMPTY_LIST);
        return erpReturnDTO;
    }
}

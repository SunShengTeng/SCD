package cn.sst.scd.feign.factory;

import cn.sst.scd.dto.ErpMaterialPar;
import cn.sst.scd.dto.ErpReturnDTO;
import cn.sst.scd.feign.ErpServiceFeignClient;
import cn.sst.scd.feign.callback.ErpServiceTimeOutFallBack;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author shengtengsun
 * @Description ERP Feign Factory
 * @Date 2020/11/16 7:30 下午
 * @Version 1.1.0
 **/
@Component
public class ErpServiceFallBackFactory implements FallbackFactory<ErpServiceFeignClient> {
    @Override
    public ErpServiceFeignClient create(Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return new ErpServiceTimeOutFallBack();
        }
        return new ErpServiceFeignClient() {
            @Override
            public ErpReturnDTO getCanRequiredNum(ErpMaterialPar materialPar) {
                ErpReturnDTO erpReturnDTO = new ErpReturnDTO();
                erpReturnDTO.setCode(0);
                erpReturnDTO.setMsg("其他异常");
                erpReturnDTO.setData(Collections.EMPTY_LIST);
                return erpReturnDTO;
            }

            @Override
            public ErpReturnDTO getShippedAmount(ErpMaterialPar materialPar) {
                ErpReturnDTO erpReturnDTO = new ErpReturnDTO();
                erpReturnDTO.setCode(0);
                erpReturnDTO.setMsg("其他异常");
                erpReturnDTO.setData(Collections.EMPTY_LIST);
                return erpReturnDTO;
            }
        };
    }
}

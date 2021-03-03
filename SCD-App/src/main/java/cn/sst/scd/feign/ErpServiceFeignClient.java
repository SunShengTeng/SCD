package cn.sst.scd.feign;

import cn.sst.scd.dto.ErpMaterialPar;
import cn.sst.scd.dto.ErpReturnDTO;
import cn.sst.scd.feign.factory.ErpServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author shengtengsun
 * @Description ERP Feign 客户端
 * @Date 2020/11/16 7:28 下午
 * @Version 1.1.0
 **/
@FeignClient(name = "${http.erp-server.name}", url = "${http.erp-server.host}", fallbackFactory = ErpServiceFallBackFactory.class)
public interface ErpServiceFeignClient {
    /**
     * 根据合同号获取物料的可要货数量
     *
     * @param materialPar: 物料参数
     * @return java.lang.Object
     * @author shengtengsun
     * @date 2020/11/16 7:41 下午
     **/
    @PostMapping("/dahua-erp-base/Orders/prjUnship")
    ErpReturnDTO getCanRequiredNum(@RequestBody ErpMaterialPar materialPar);

    /**
     * 获取每个物料已发货总量、已取消总量。
     *
     * @param materialPar: 物料参数
     * @return java.lang.Object
     * @author shengtengsun
     * @date 2020/11/16 7:41 下午
     **/
    @PostMapping("/dahua-erp-base/Orders/prjShip")
    ErpReturnDTO getShippedAmount(@RequestBody ErpMaterialPar materialPar);

}

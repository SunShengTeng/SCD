package cn.sst.scd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/11/16 7:14 下午
 * @Version 1.1.0
 **/
@Data
@Component
@ConfigurationProperties("http.erp")
public class ErpHttpConfig {
    /**
     * 获取物料的可要货数量
     **/
    private HttpServiceConfig canRequiredNum;
    /**
     * 获取物料的已发货总量、已取消总量
     **/
    private HttpServiceConfig shipAndCancelQty;
}

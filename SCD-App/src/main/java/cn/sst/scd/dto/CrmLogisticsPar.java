package cn.sst.scd.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shengtengsun
 * @Description CRM 获取物流时效请求参数
 * @Date 2020/11/18 上午11:37
 * @Version 1.1.0
 **/
@Data
public class CrmLogisticsPar implements Serializable {
    /**
     * 省
     **/
    private String origin;
    /**
     * 市
     **/
    private String city;
    /**
     * 区
     **/
    private String area;
}

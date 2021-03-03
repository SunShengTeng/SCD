package cn.sst.scd.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shengtengsun
 * @Description CRM 服务返回DTO
 * @Date 2020/11/18 上午11:40
 * @Version 1.1.0
 **/
@Data
public class CrmReturnDTO implements Serializable {
    /**
     * success = true 表示请求成功
     **/
    private Boolean success;
    /**
     * (CRM未定义)
     **/
    private String resultCode;
    /**
     * (CRM未定义)
     **/
    private String resultMessage;
    /**
     * 业务数据
     **/
    private Object data;
}

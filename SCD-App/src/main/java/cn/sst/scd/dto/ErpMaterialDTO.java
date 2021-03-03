package cn.sst.scd.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author shengtengsun
 * @Description Erp物料信息
 * @Date 2020/11/17 8:49 上午
 * @Version 1.1.0
 **/
@Data
public class ErpMaterialDTO implements Serializable {
    /**
     * 项目编号/合同编号
     **/
    private String projectNumber;
    /**
     * 物料编号
     **/
    private String itemNo;
    /**
     * SCP可要货量
     **/
    private BigDecimal unshipQty;
    /**
     * 未承诺量(未返回真实数据)
     **/
    private BigDecimal unprepareQty;
    /**
     * 已发货总量
     **/
    private BigDecimal shipQty;
    /**
     * 已取消总量
     **/
    private BigDecimal cancelQty;
}

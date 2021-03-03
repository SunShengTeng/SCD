package cn.sst.scd.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author shengtengsun
 * @Description Erp获取物料信息的参数
 * @Date 2020/11/17 8:44 上午
 * @Version 1.1.0
 **/
@Data
public class ErpMaterialPar implements Serializable {
    /**
     * 合同号
     **/
    private String projectNumber;
    /**
     * 物料号数组
     **/
    private List<String> itemList;

}

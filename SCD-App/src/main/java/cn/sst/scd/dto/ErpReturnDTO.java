package cn.sst.scd.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author shengtengsun
 * @Description Erp 服务返回DTO
 * @Date 2020/11/17 8:47 上午
 * @Version 1.1.0
 **/
@Data
public class ErpReturnDTO implements Serializable {
    private String msg;
    /**
     * code = 0 表示成功
     **/
    private Integer code;
    private List<ErpMaterialDTO> data;

}

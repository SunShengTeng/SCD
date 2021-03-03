package cn.sst.scd.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author shengtengsun
 * @Description 物料管理: 滚动要货-要货计划DTO
 * @Date 2020/11/6 2:23 下午
 * @Version 1.1.0
 **/
@Data
@Builder
public class DistributePlanDTO implements Serializable {
    /**
     * 主键
     **/
    private Long id;
    /**
     * 计划要货时间(用字符串是因为这个字段不仅仅用于存储时间，还来存储"第N周汇总"这种字符串)
     **/
    private String planTime;
    /**
     * 计划要货数量
     **/
    private BigDecimal planNum;
    /**
     * 自上次发布后有没有修改过(0:没有修改过，1:修改过)
     **/
    private Integer hasModify;

    public DistributePlanDTO(Long id, String planTime, BigDecimal planNum, Integer hasModify) {
        this.id = id;
        this.planTime = planTime;
        this.planNum = planNum;
        this.hasModify = hasModify;
    }
}

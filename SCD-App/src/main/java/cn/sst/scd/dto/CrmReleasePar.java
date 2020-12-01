package cn.sst.scd.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author shengtengsun
 * @Description CRM 发布物料参数
 * @Date 2020/11/18 下午1:03
 * @Version 1.1.0
 **/
@Data
public class CrmReleasePar implements Serializable {
    /**
     * 提交人工号(非必传)
     **/
    private Long userId;
    /**
     * 发布的物料信息
     **/
    private List<MaterialPar> data;

    @Data
    @Builder
    public static class MaterialPar {
        /**
         * 项目ID(非必传：默认0)
         **/
        private String PROJECT_ID;
        /**
         * 项目编号（必传）
         **/
        private String PROJECT_NUM;
        /**
         * 物料号(必传)
         **/
        private String ITEM_NUMBER;
        /**
         * 总需求数量(必传)
         **/
        private BigDecimal REQUIRE_TOTAL;
        /**
         * 已下单数量(必传)
         **/
        private BigDecimal ORDER_QTY;
        /**
         * 要货数量(必传)
         **/
        private BigDecimal REQUIRE_QTY;
        /**
         * 需求到货日期(必传)
         **/
        private String REQUIRE_ARRIVAL_TIME;
    }
}

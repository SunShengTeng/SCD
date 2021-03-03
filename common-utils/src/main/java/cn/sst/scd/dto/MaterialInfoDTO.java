package cn.sst.scd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author shengtengsun
 * @Description 滚动要货：列表页的物料信息实体
 * @Date 2020/11/6 1:35 下午
 * @Version 1.1.0
 **/
@Data
@Builder
public class MaterialInfoDTO implements Serializable, Cloneable {
    /**
     * 物料的业务主键
     **/
    private String materialInfoId;
    /**
     * 物料号
     **/
    private String partNum;
    /**
     * 产品名称(物料名)
     **/
    private String prodDesc;
    /**
     * 规格型号
     **/
    private String externalModel;
    /**
     * 单位
     **/
    private String productUnit;
    /**
     * 总需求
     **/
    private BigDecimal qty;
    /**
     * 己备货数量
     **/
    private BigDecimal crmOrderedQuantity;
    /**
     * CRM己发货数量
     **/
    private BigDecimal ebsShippedQty;
    /**
     * SCP可要货量
     **/
    private BigDecimal canRequiredNum;
    /**
     * SCP未承诺数量
     **/
    private BigDecimal noPromiseNum;
    /**
     * 物料的"可要货数量/供应链承诺"数组
     **/
    private List<DistributePlanDTO> timeArr;
    /**
     * 数据类型(1:要货数量,2:供应链承诺)
     **/
    private Integer dataType;
    /**
     * 修改时间
     **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;
    /**
     * 修改人
     **/
    private String modifier;
    /**
     * 修改工号
     **/
    private String modifierLoginName;
    /**
     * 提交人姓名
     **/
    private String submitUser;
    /**
     * 提交人工号
     **/
    private String submitLoginName;
    /**
     * 提交时间(isdp中该记录最新的发布时间)
     **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;
    /************************************  非物料信息 *****************************************/
    /**
     * 历史数量
     **/
    private BigDecimal historyNum;
    /**
     * 将来数量
     **/
    private BigDecimal futureNum;
    /**
     * 供应链回复时间
     **/
    private String answerTime;
    /**
     * 自上次发布后有没有修改过,仅用于前端显示(0:没有修改过，1:修改过)
     **/
    private Integer hasModify;

    public MaterialInfoDTO() {
    }

    public MaterialInfoDTO(String materialInfoId, String partNum, String prodDesc, String externalModel, String productUnit, BigDecimal qty, BigDecimal crmOrderedQuantity, BigDecimal ebsShippedQty, BigDecimal canRequiredNum, BigDecimal noPromiseNum, List<DistributePlanDTO> timeArr, Integer dataType, Date modifyDate, String modifier, String modifierLoginName, String submitUser, String submitLoginName, Date submitTime, BigDecimal historyNum, BigDecimal futureNum, String answerTime, Integer hasModify) {
        this.materialInfoId = materialInfoId;
        this.partNum = partNum;
        this.prodDesc = prodDesc;
        this.externalModel = externalModel;
        this.productUnit = productUnit;
        this.qty = qty;
        this.crmOrderedQuantity = crmOrderedQuantity;
        this.ebsShippedQty = ebsShippedQty;
        this.canRequiredNum = canRequiredNum;
        this.noPromiseNum = noPromiseNum;
        this.timeArr = timeArr;
        this.dataType = dataType;
        this.modifyDate = modifyDate;
        this.modifier = modifier;
        this.modifierLoginName = modifierLoginName;
        this.submitUser = submitUser;
        this.submitLoginName = submitLoginName;
        this.submitTime = submitTime;
        this.historyNum = historyNum;
        this.futureNum = futureNum;
        this.answerTime = answerTime;
        this.hasModify = hasModify;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public BigDecimal getCanRequiredNum() {
        return canRequiredNum == null ? BigDecimal.ZERO : canRequiredNum;
    }

    public BigDecimal getNoPromiseNum() {
        return noPromiseNum == null ? BigDecimal.ZERO : noPromiseNum;
    }
}

package cn.sst.scd.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author shengtengsun
 * @Description 获取供应链承诺请求参数
 * @Date 2020/11/18 下午6:35
 * @Version 1.1.0
 **/
@Data
public class CrmProviderPromisePar implements Serializable {
    /**
     * 项目编号
     **/
    private String projectNum;
    /**
     * 物料号数组
     **/
    private List<String> materialNumArr;
    /**
     * 当前时间字符串(格式：yyyy-MM-dd)
     **/
    private String currentTime;
    /**
     * 当前时间前几天
     **/
    private Integer before;
    /**
     * 当前时间后几周
     **/
    private Integer after;

}

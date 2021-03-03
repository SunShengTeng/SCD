package cn.sst.scd.annotation;

import cn.sst.scd.enums.SystemOperatorType;

import java.lang.annotation.*;

/**
 * 记录系统操作日志
 *
 * @author shengtengsun
 * @date 2020/9/25 3:13 下午
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableOperatorLog {
    /**
     * 操作类型
     *
     * @see SystemOperatorType
     **/
    SystemOperatorType operatorType() default SystemOperatorType.OTHER;

    /**
     * 操作人姓名 @nullable
     **/
    String name() default "";

    /**
     * 一级模块
     **/
    String priModel();

    /**
     * 二级模块
     **/
    String secModel() default "";

    /**
     * 三级模块
     **/
    String thirdModel() default "";

    /**
     * 操作工号
     **/
    String number() default "";

    /**
     * 操作备注
     **/
    String note() default "";

    /**
     * 项目ID
     **/
    String projectId() default "";
}

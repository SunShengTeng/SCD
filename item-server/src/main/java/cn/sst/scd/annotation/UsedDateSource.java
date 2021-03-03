package cn.sst.scd.annotation;

import cn.sst.scd.datasource.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shengtengsun
 * @Description 数据源切换注解
 * @Date 2020/7/28 4:17 下午
 * @Version 1.1.0
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsedDateSource {
    DataSourceType value() default DataSourceType.account;
}

package cn.sst.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shengtengsun
 * @Description Logger请求参数
 * @Date 2020/7/28 11:19 上午
 * @Version 1.1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerRequestParam {
}

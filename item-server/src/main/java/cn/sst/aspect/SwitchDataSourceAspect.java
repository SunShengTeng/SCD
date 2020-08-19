package cn.sst.aspect;

import cn.sst.annotation.UsedDateSource;
import cn.sst.datasource.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author shengtengsun
 * @Description 切换数据源切面
 * @Date 2020/7/28 4:19 下午
 * @Version 1.1.0
 **/
@Component
@Aspect
public class SwitchDataSourceAspect {

    @Around("@annotation(cn.sst.annotation.UsedDateSource)")
    public Object updateDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        Object rest = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        UsedDateSource dateSource = signature.getMethod().getDeclaredAnnotation(UsedDateSource.class);
        if (null != dateSource) {
            System.out.println("注解内容：" + dateSource.value());
            DataSourceContextHolder.setDataSource(dateSource.value());
        }
        rest = joinPoint.proceed();
        DataSourceContextHolder.resetDataSource();
        return rest;
    }

}

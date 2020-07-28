package cn.sst.aspect;

import cn.sst.annotation.UsedDateSource;
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
    public Object updateDataSource(ProceedingJoinPoint joinPoint) {
        Object rest = null;
        System.out.println("==================== before ====================");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println(signature.getDeclaringType());
        System.out.println(signature.getDeclaringTypeName());
        System.out.println(signature.getModifiers());
        System.out.println(signature.getName());
        UsedDateSource dateSource = signature.getMethod().getDeclaredAnnotation(UsedDateSource.class);
        System.out.println("注解内容：" + dateSource.value());
        try {
            rest = joinPoint.proceed();
            System.out.println("==================== after ====================");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            System.out.println("==================== finally ====================");
        }
        return rest;
    }

}

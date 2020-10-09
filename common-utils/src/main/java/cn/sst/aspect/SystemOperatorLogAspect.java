package cn.sst.aspect;

import cn.sst.annotation.EnableOperatorLog;
import cn.sst.dto.UserInfoDTO;
import cn.sst.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author shengtengsun
 * @Description 记录操作日志切面
 * @Date 2020/9/25 4:34 下午
 * @Version 1.1.0
 **/
@Component
@Aspect
@Slf4j
public class SystemOperatorLogAspect {

    @Around("@annotation(cn.sst.annotation.EnableOperatorLog)")
    public Object recordOperatorLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();

        ThreadPoolExecutor executor = ThreadPoolUtil.createLogThreadPoolExecutor();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            createOperationRecord(joinPoint, null);
            return 3;
        }, executor);
        future.thenAccept(e -> System.out.println(e));
        return proceed;
    }

    /**
     * 操作成功记录
     *
     * @param joinPoint
     * @return void
     * @author shengtengsun
     * @date 2020/9/28 4:25 下午
     **/
    private void createOperationRecord(JoinPoint joinPoint, Exception e) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        EnableOperatorLog declaredAnnotation = methodSignature.getMethod().getDeclaredAnnotation(EnableOperatorLog.class);
        if (null == declaredAnnotation) {
            return;
        }
        // 1、解析当前操作用户
        UserInfoDTO user = obtainUserInfo(declaredAnnotation);
        // 2、获取发起请求的IP地址
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        /*final String ipAddress = NetworkUtil.getIpAddress(requestAttributes.getRequest());

        final String projectId = declaredAnnotation.projectId();
        final SystemOperatorType systemOperatorType = declaredAnnotation.operatorType();

        final String priModel = declaredAnnotation.priModel();
        final String secModel = declaredAnnotation.secModel();
        final String thirdModel = declaredAnnotation.thirdModel();
        final String operatorNote = declaredAnnotation.note();

        final String className = joinPoint.getTarget().getClass().getName();
        final String methodName = methodSignature.getMethod().getName();

        OperationLogDTO operationLogDTO = new OperationLogDTO() {
            {
                setUser(user);
                setIpAddress(ipAddress);
                setMethodName(methodName);
                setClassName(className);
                setPriModel(priModel);
                setSecModel(secModel);
                setThirdModel(thirdModel);
                setOperatorNote(operatorNote);
                setOperatorTime(new Date());
                setOperatorType(systemOperatorType);
                setProjectId(projectId);
                setSuccessFlag(1);
            }
        };*/
        System.out.println("日志线程:" + Thread.currentThread().getName());
    }


    @AfterThrowing(pointcut = "@annotation(cn.sst.annotation.EnableOperatorLog)", throwing = "e")
    public void handleThrowing(JoinPoint joinPoint, Exception e) {
        createOperationRecord(joinPoint, e);
    }

    /**
     * 获取用户信息
     *
     * @param declaredAnnotation
     * @return {@link UserInfoDTO}
     * @author shengtengsun
     * @date 2020/9/25 5:19 下午
     **/
    private UserInfoDTO obtainUserInfo(EnableOperatorLog declaredAnnotation) {
        UserInfoDTO res = new UserInfoDTO();
        String name = declaredAnnotation.name();
        String number = declaredAnnotation.number();
        res.setLoginName(number);
        res.setNickname(name);
        return res;
    }
}

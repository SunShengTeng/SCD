package cn.sst.aspect;

import cn.sst.annotation.EnableOperatorLog;
import cn.sst.dto.OperationLogDTO;
import cn.sst.dto.UserInfoDTO;
import cn.sst.enums.SystemOperatorType;
import cn.sst.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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

    private static final String PROPERTY_ID = "id";

    private static final String LANG_PACKAGE_NAME = "java.lang";

    @Around("@annotation(cn.sst.annotation.EnableOperatorLog)")
    public Object recordOperatorLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();

        CompletableFuture.runAsync(() -> {
            createOperationRecord(joinPoint, null);
        }, ThreadPoolUtil.createLogThreadPoolExecutor());

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

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        OperationLogDTO operationLogDTO = new OperationLogDTO();
        analyzingOperationTypeAndSetProjectId(joinPoint, operationLogDTO);

        System.out.println("");
        System.out.println("日志线程:" + Thread.currentThread().getName());
    }

    /**
     * 解析参数更新操作类型(区分是保存、更新)，并获取项目ID
     *
     * @param joinPoint
     * @param operationLogDTO
     * @return void
     * @author shengtengsun
     * @date 2020/9/29 5:14 下午
     **/
    private void analyzingOperationTypeAndSetProjectId(JoinPoint joinPoint, OperationLogDTO operationLogDTO) {

        operationLogDTO.setOperatorType(SystemOperatorType.INSERT);

        List<Object> paramList = Arrays.asList(joinPoint.getArgs());
        for (Object par :
                paramList) {
            // Map
            if (par instanceof Map) {
                Map paramMap = (Map) par;
                Object id = paramMap.get(PROPERTY_ID);
                if (id != null && StringUtils.isNotBlank(String.valueOf(id))) {
                    operationLogDTO.setOperatorType(SystemOperatorType.UPDATE);
                }
                break;
            }
            // Collection
            if (par instanceof Collection && !CollectionUtils.isEmpty((Collection) par)) {
                parseParameter(((Collection) par).toArray()[0], operationLogDTO);
                break;
            }
            // Object[]
            if (par instanceof Object[]) {
                parseParameter(((Object[]) par)[0], operationLogDTO);
                break;
            }
            parseParameter(par, operationLogDTO);
        }
    }

    /**
     * 遍历参数的所有属性，依次分析属性名是否为id
     *
     * @param o               参数值
     * @param operationLogDTO 业务对象
     * @return void
     * @author shengtengsun
     * @date 2020/10/12 9:34 上午
     **/
    void parseParameter(Object o, OperationLogDTO operationLogDTO) {

        if (null == o || null == operationLogDTO) {
            return;
        }

        List<Field> fields = Arrays.asList(o.getClass().getDeclaredFields());
        for (Field field :
                fields) {
            // 基本数据类型
            if (field.getType().isPrimitive()) {
                Object fieldValue = getValueOfGetIncludeObjectField(o, PROPERTY_ID);
                if (null != fieldValue && StringUtils.isNotBlank(String.valueOf(fieldValue))) {
                    operationLogDTO.setOperatorType(SystemOperatorType.UPDATE);
                    break;
                }
                continue;
            }
            // 非基础包装类
            String packageName = field.getType().getPackage().getName();
            if (!LANG_PACKAGE_NAME.equals(packageName)) {
                Object fieldValue = getValueOfGetIncludeObjectField(o, field.getName());
                parseParameter(fieldValue, operationLogDTO);
                continue;
            }
            // 基础包装类
            if (PROPERTY_ID.equals(field.getName())) {
                Object fieldValue = getValueOfGetIncludeObjectField(o, PROPERTY_ID);
                if (null != fieldValue && StringUtils.isNotBlank(String.valueOf(fieldValue))) {
                    operationLogDTO.setOperatorType(SystemOperatorType.UPDATE);
                    break;
                }
            }
        }
    }


    /**
     * 通过字段名从对象或对象的父类中得到字段的值（调用字典的get方法，可以取出复杂的对象的值）
     *
     * @param object
     * @param fieldName
     * @return java.lang.Object
     * @author shengtengsun
     * @date 2020/10/12 9:34 上午
     **/
    public static Object getValueOfGetIncludeObjectField(Object object, String fieldName) {

        if (object == null || StringUtils.isBlank(fieldName)) {
            return null;
        }

        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                if (fieldName.contains(".")) {
                    // 如：operatorUser.name、operatorUser.org.name，递归调用
                    String[] splitFiledName = fieldName.split("\\.");
                    return getValueOfGetIncludeObjectField(
                            getValueOfGetIncludeObjectField(object, splitFiledName[0]),
                            splitFiledName[1]);
                }
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);

                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                //获得get方法
                Method getMethod = pd.getReadMethod();
                //执行get方法返回一个Object
                return getMethod.invoke(object);
            } catch (Exception e) {
            }
        }
        return null;
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

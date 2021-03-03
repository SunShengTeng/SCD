package cn.sst.scd.aspect;

import cn.sst.scd.annotation.EnableOperatorLog;
import cn.sst.scd.dto.OperationLogDTO;
import cn.sst.scd.dto.UserInfoDTO;
import cn.sst.scd.enums.SystemOperatorType;
import cn.sst.scd.util.ThreadPoolUtil;
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
import java.util.*;
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
    /**
     * 大华逻辑删除
     **/
    private static final String DA_HUA_IS_DEL = "isDel";

    private static final String LANG_PACKAGE_NAME = "java.lang";

    @Around("@annotation(cn.sst.scd.annotation.EnableOperatorLog)")
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
        analyzingOperationTypeForInsertOrUpdate(joinPoint, operationLogDTO);

        // 如果是更新，则判断是否为删除(仅仅为了支持大华的'isDel'架构)
        if (operationLogDTO.getOperatorType().equals(SystemOperatorType.UPDATE)) {
            analyzingOperationTypeForDelete(joinPoint, operationLogDTO);
        }

        System.out.println("");
        System.out.println("日志线程:" + Thread.currentThread().getName());
    }

    /**
     * 分析操作类型是否为逻辑删除(大华：isDel为1)
     *
     * @param joinPoint
     * @param operationLogDTO
     * @return void
     * @author shengtengsun
     * @date 2020/10/12 10:34 上午
     **/
    private void analyzingOperationTypeForDelete(JoinPoint joinPoint, OperationLogDTO operationLogDTO) {

        List<Object> paramList = Arrays.asList(joinPoint.getArgs());
        for (Object par :
                paramList) {
            // Map
            if (par instanceof Map) {
                Map paramMap = (Map) par;
                Object isDel = paramMap.get(DA_HUA_IS_DEL);
                if (isDel != null && String.valueOf(isDel).equals("1")) {
                    operationLogDTO.setOperatorType(SystemOperatorType.DELETE);
                }
                break;
            }
            // Collection
            if (par instanceof Collection && !CollectionUtils.isEmpty((Collection) par)) {
                parseParameter(((Collection) par).toArray()[0], operationLogDTO, true);
                break;
            }
            // Object[]
            if (par instanceof Object[]) {
                parseParameter(((Object[]) par)[0], operationLogDTO, true);
                break;
            }
            parseParameter(par, operationLogDTO, true);
        }
    }

    /**
     * 分析操作类型是更新还是新增
     * 通过判断方法参数ID属性是否为null
     *
     * @param joinPoint
     * @param operationLogDTO
     * @return void
     * @author shengtengsun
     * @date 2020/9/29 5:14 下午
     **/
    private void analyzingOperationTypeForInsertOrUpdate(JoinPoint joinPoint, OperationLogDTO operationLogDTO) {

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
                parseParameter(((Collection) par).toArray()[0], operationLogDTO,false);
                break;
            }
            // Object[]
            if (par instanceof Object[]) {
                parseParameter(((Object[]) par)[0], operationLogDTO,false);
                break;
            }
            parseParameter(par, operationLogDTO,false);
        }
    }

    /**
     * 解析参数属性
     *
     * @param o               参数值
     * @param operationLogDTO 业务对象
     * @param isDelete        是否判断删除(默认否)
     * @return void
     * @author shengtengsun
     * @date 2020/10/12 9:34 上午
     **/
    void parseParameter(Object o, OperationLogDTO operationLogDTO, Boolean isDelete) {

        if (null == o || null == operationLogDTO) {
            return;
        }
        if (null == isDelete) {
            isDelete = false;
        }

        // 获取所有字段(含父类)
        ArrayList<Field> fields = new ArrayList<>();
        Class<?> clazz = o.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        // switch field type
        for (Field field :
                fields) {
            // base data type
            if (field.getType().isPrimitive()) {
                Object fieldValue = getValueOfGetIncludeObjectField(o, isDelete ? DA_HUA_IS_DEL : PROPERTY_ID);
                String strVal = String.valueOf(fieldValue);
                if (null != fieldValue && StringUtils.isNotBlank(strVal)) {
                    operationLogDTO.setOperatorType(strVal.equals('1') ? SystemOperatorType.DELETE : SystemOperatorType.UPDATE);
                    break;
                }
                continue;
            }
            // ref no wrap type
            String packageName = field.getType().getPackage().getName();
            if (!LANG_PACKAGE_NAME.equals(packageName)) {
                Object fieldValue = getValueOfGetIncludeObjectField(o, field.getName());
                parseParameter(fieldValue, operationLogDTO, isDelete);
                continue;
            }
            // ref wrap type
            Object fieldValue = getValueOfGetIncludeObjectField(o, isDelete ? DA_HUA_IS_DEL : PROPERTY_ID);
            String strVal = String.valueOf(fieldValue);
            if (null != fieldValue && StringUtils.isNotBlank(strVal)) {
                operationLogDTO.setOperatorType(strVal.equals("1") ? SystemOperatorType.DELETE : SystemOperatorType.UPDATE);
                break;
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

    @AfterThrowing(pointcut = "@annotation(cn.sst.scd.annotation.EnableOperatorLog)", throwing = "e")
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

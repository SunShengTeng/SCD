package cn.sst.dto;

import cn.sst.enums.SystemOperatorType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shengtengsun
 * @Description 系统操作日志DTO
 * @Date 2020/9/27 11:07 上午
 * @Version 1.1.0
 **/
public class OperationLogDTO implements Serializable {
    private UserInfoDTO user;
    private String ipAddress;
    private String priModel;
    private String secModel;
    private String thirdModel;
    private SystemOperatorType operatorType;
    private String methodName;
    private String operatorNote;
    private Date operatorTime;

    private String projectId;
    private String className;
    private Integer successFlag;
    private String errorMsg;


    public UserInfoDTO getUser() {
        return user;
    }

    public void setUser(UserInfoDTO user) {
        this.user = user;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPriModel() {
        return priModel;
    }

    public void setPriModel(String priModel) {
        this.priModel = priModel;
    }

    public String getSecModel() {
        return secModel;
    }

    public void setSecModel(String secModel) {
        this.secModel = secModel;
    }

    public String getThirdModel() {
        return thirdModel;
    }

    public void setThirdModel(String thirdModel) {
        this.thirdModel = thirdModel;
    }

    public SystemOperatorType getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(SystemOperatorType operatorType) {
        this.operatorType = operatorType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(Integer successFlag) {
        this.successFlag = successFlag;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getOperatorNote() {
        return operatorNote;
    }

    public void setOperatorNote(String operatorNote) {
        this.operatorNote = operatorNote;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    @Override
    public String toString() {
        return "OperationLogDTO{" +
                "user=" + user +
                ", ipAddress='" + ipAddress + '\'' +
                ", priModel='" + priModel + '\'' +
                ", secModel='" + secModel + '\'' +
                ", thirdModel='" + thirdModel + '\'' +
                ", operatorType=" + operatorType +
                ", methodName='" + methodName + '\'' +
                ", operatorNote='" + operatorNote + '\'' +
                ", operatorTime=" + operatorTime +
                ", projectId='" + projectId + '\'' +
                ", className='" + className + '\'' +
                ", successFlag=" + successFlag +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}

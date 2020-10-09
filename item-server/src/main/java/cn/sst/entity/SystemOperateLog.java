package cn.sst.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

/**
 * @author shengtengsun
 * @Description 系统操作日志实体
 * @Date 2020/9/25 3:26 下午
 * @Version 1.1.0
 **/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
@DynamicInsert
@Table(name = "sys_opt_log"
        , indexes = {
        @Index(name = "idx_opt_name_or_number", columnList = "operator_name"),
        @Index(name = "idx_opt_name_or_number", columnList = "operator_number"),
        @Index(name = "idx_project", columnList = "project_Id"),
        @Index(name = "idx_model", columnList = "pri_model"),
        @Index(name = "idx_model", columnList = "sec_model"),
        @Index(name = "idx_model", columnList = "third_model"),
}
)
@org.hibernate.annotations.Table(appliesTo = "sys_opt_log", comment = "系统操作日志")
public class SystemOperateLog implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_Id", columnDefinition = "VARCHAR(25) NULL COMMENT '项目ID'")
    private String projectId;

    @Column(name = "ip_address", columnDefinition = "VARCHAR(25) NULL COMMENT '操作IP地址'")
    private String ipAddress;

    @Column(name = "operator_name", columnDefinition = "VARCHAR(45) NOT NULL COMMENT '操作人姓名'")
    private String operatorName;

    @Column(name = "operator_number", columnDefinition = "VARCHAR(20) NULL COMMENT '操作人工号'")
    private String operatorNumber;

    @Column(name = "pri_model", columnDefinition = "VARCHAR(25) NOT NULL COMMENT '一级操作模块'")
    private String priModel;

    @Column(name = "sec_model", columnDefinition = "VARCHAR(25)  NULL COMMENT '二级操作模块'")
    private String secModel;

    @Column(name = "third_model", columnDefinition = "VARCHAR(25)  NULL COMMENT '三级操作模块'")
    private String thirdModel;

    @Column(name = "operator_type", columnDefinition = "TINYINT(2) NOT NULL COMMENT '操作类型(1：新增，2：修改，3：删除，4：其他)'")
    private Integer operatorType;

    @Column(name = "class_name", columnDefinition = "VARCHAR(45) NOT NULL COMMENT '类名'")
    private String className;

    @Column(name = "method_name", columnDefinition = "VARCHAR(45) NOT NULL COMMENT '方法名'")
    private String methodName;

    @Column(name = "operator_note", columnDefinition = "VARCHAR(255) NULL COMMENT '操作备注'")
    private String operatorNote;

    @Column(name = "operator_time", columnDefinition = "DATETIME NOT NULL COMMENT '操作时间'")
    private Date operatorTime;

    @Column(name = "success_flag", columnDefinition = "TINYINT(2) NOT NULL DEFAULT 1 COMMENT '操作成功标志(1：成功，2：失败'")
    private Integer successFlag;

    @Column(name = "error_msg", columnDefinition = "VARCHAR(255) NULL COMMENT '操作失败日志'")
    private String errorMsg;

}

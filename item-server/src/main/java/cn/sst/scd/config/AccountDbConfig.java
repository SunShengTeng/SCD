package cn.sst.scd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName BookingDbConfig
 * @Description 预约中心：数据库链接配置
 * @Author shengtengsun
 * @Date 2019-07-19 13:08
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.account")
public class AccountDbConfig {
    private String url;
    private String driverClassName;
    /**
     * 指定连接池最大的连接数，包括使用中的和空闲的连接.
     */
    private int maximumPoolSize;
    /**
     * 指定连接维护的最小空闲连接数，当使用HikariCP时指定
     */
    private int minimumIdle;
    /**
     * 数据库链接密码
     */
    private String password;
    /**
     * 数据库链接用户名
     */
    private String userName;
}

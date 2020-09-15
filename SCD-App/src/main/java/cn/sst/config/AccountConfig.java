package cn.sst.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/9/10 5:19 下午
 * @Version 1.1.0
 **/
@ConfigurationProperties(value = "account")
public class AccountConfig {
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

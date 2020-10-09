package cn.sst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author shengtengsun
 * @Description 应用启动类
 * @Date 2020/7/27 5:00 下午
 * @Version 1.1.0
 **/
@EnableEurekaClient
@SpringBootApplication
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}

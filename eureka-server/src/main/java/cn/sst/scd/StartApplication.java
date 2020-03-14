package cn.sst.scd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/3/14 11:33 上午
 * @Version 1.1.0
 **/
@EnableEurekaServer
@SpringBootApplication
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}

package cn.sst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shengtengsun
 * @Description 应用启动类
 * @Date 2020/7/28 2:38 下午
 * @Version 1.1.0
 **/
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SCDApplication {
    public static void main(String[] args) {
        SpringApplication.run(SCDApplication.class, args);
    }
}

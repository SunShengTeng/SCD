package cn.sst.scd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author shengtengsun
 * @Description 库存中心启动类
 * @Date 2020/10/9 4:11 下午
 * @Version 1.1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "cn.sst.scd.dao")
public class InventoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }
}

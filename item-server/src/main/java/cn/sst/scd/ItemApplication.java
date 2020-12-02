package cn.sst.scd;

import cn.sst.scd.nio.ItemNioClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author shengtengsun
 * @Description 应用启动类
 * @Date 2020/7/27 5:00 下午
 * @Version 1.1.0
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class ItemApplication {

    public static void main(String[] args) {
        // 启动Client Of ItemServer
        ItemNioClient.startClient();
        // 启动Spring
        SpringApplication.run(ItemApplication.class, args);
    }
}

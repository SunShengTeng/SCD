package cn.sst.scd;

import cn.sst.scd.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/3/14 5:22 下午
 * @Version 1.1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    /**
     * @return cn.sst.scd.filter.TokenFilter
     * @author shengtengsun
     * @Description 将过滤器交给Spring管理
     * @Date 2020/3/14 5:52 下午
     * @Param []
     **/
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}

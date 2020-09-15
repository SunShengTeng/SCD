package cn.sst.config;

import cn.sst.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shengtengsun
 * @Description SpringMVC配置
 * @Date 2020/7/28 2:30 下午
 * @Version 1.1.0
 **/
@Configuration
public class ScdWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
    }


}

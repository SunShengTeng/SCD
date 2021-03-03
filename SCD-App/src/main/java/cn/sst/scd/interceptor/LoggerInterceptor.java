package cn.sst.scd.interceptor;

import cn.sst.scd.annotation.LoggerRequestParam;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @author shengtengsun
 * @Description Controller请求参数Log拦截器
 * @Date 2020/7/28 1:54 下午
 * @Version 1.1.0
 **/
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod methodHandle = (HandlerMethod) handler;
            LoggerRequestParam requestParam = methodHandle.getMethod().getAnnotation(LoggerRequestParam.class);
            if (null != requestParam) {
                // LoggerFactory
                System.out.println("============================== request Param start ==============================");
                Iterator<Map.Entry<String, String[]>> entryIterator =
                        request.getParameterMap().entrySet().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, String[]> entry = entryIterator.next();
                    System.out.print("参数名：" + entry.getKey() + ", 参数值：");
                    Arrays.stream(entry.getValue()).forEach(System.out::print);
                    System.out.println(";");
                }
                System.out.println("============================== request Param end ==============================");
            }
        }
        return true;
    }
}

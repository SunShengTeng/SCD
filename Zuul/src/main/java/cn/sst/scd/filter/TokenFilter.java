package cn.sst.scd.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shengtengsun
 * @Description 自定义的Token拦截器
 * @Date 2020/3/14 5:43 下午
 * @Version 1.1.0
 **/
public class TokenFilter extends ZuulFilter {
    /**
     * //四种类型：pre,routing,error,post
     * //pre：主要用在路由映射的阶段是寻找路由映射表的
     * //routing:具体的路由转发过滤器是在routing路由器，具体的请求转发的时候会调用
     * //error:一旦前面的过滤器出错了，会调用error过滤器。
     * //post:当routing，error运行完后才会调用该过滤器，是在最后阶段的
     **/
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @return int
     * @Description 自定义过滤器执行的顺序，数值越大越靠后执行，越小就越先执行
     * @author shengtengsun
     * @Date 2020/3/14 5:46 下午
     * @Param []
     **/
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @return boolean
     * @Description 控制过滤器生效不生效，可以在里面写一串逻辑来控制
     * @author shengtengsun
     * @Date 2020/3/14 5:46 下午
     * @Param []
     **/
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * @return java.lang.Object
     * @Description 执行过滤逻辑
     * @author shengtengsun
     * @Date 2020/3/14 5:45 下午
     * @Param []
     **/
    @Override
    public Object run() {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getParameter("token");
        if (token == null) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.setResponseBody("unAuthrized");


            return null;
        }
        return null;
    }
}

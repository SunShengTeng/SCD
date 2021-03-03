package cn.sst.scd.config;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/11/16 7:00 下午
 * @Version 1.1.0
 **/
public class HttpServiceConfig {
    private String host;
    private String uri;
    private String method;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "HttpServiceConfig{" +
                "host='" + host + '\'' +
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}

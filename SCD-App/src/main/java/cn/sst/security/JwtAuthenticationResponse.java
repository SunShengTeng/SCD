package cn.sst.security;

import java.io.Serializable;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/9/24 2:43 下午
 * @Version 1.1.0
 **/
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 4784951536404964122L;
    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}

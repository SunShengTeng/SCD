/*
package cn.sst.security;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

*/
/**
 * @author shengtengsun
 * @Description 权限实体
 * @Date 2020/9/24 1:58 下午
 * @Version 1.1.0
 **//*

public class Authority implements Serializable, GrantedAuthority {
    private Integer id;
    private AuthorityName authorityName;

    public Authority() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorityName getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(AuthorityName authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public String getAuthority() {
        return authorityName.name();
    }
}
*/

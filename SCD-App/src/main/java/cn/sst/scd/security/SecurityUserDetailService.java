/*
package cn.sst.security;

import cn.sst.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

*/
/**
 * @author shengtengsun
 * @Description
 * @Date 2020/9/24 2:30 下午
 * @Version 1.1.0
 **//*

// @Service
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByName(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户：" + username + "不存在");
        }
        return user;
    }
}
*/

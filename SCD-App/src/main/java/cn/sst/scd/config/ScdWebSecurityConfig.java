/*
package cn.sst.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

*/
/**
 * @author shengtengsun
 * @Description
 * @Date 2020/9/23 2:09 下午
 * @Version 1.1.0
 **//*

//@EnableWebSecurity
public class ScdWebSecurityConfig extends WebSecurityConfigurerAdapter {

    */
/*@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;*//*


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
        super.configure(auth);
    }

}
*/

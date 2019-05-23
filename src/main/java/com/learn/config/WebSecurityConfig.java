package com.learn.config;


import com.learn.security.AuthFilter;
import com.learn.security.AuthProvider;
import com.learn.security.LoginAuthFailHandler;
import com.learn.security.LoginUrlEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @Author： XO
 * @Description：
 * @Date： 2018/11/21 15:54
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * HTTP权限控制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

       http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);

        // 资源访问权限
        http.authorizeRequests()
                .antMatchers("/admin/login").permitAll() // 管理员登录入口
                .antMatchers("/static/**").permitAll() // 静态资源
                .antMatchers("/user/login").permitAll() // 用户登录入口
                .antMatchers("/admin/**").hasRole("ADMIN")
               // .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/user/**").hasAnyRole("ADMIN",
                "USER")
                .and()
                .formLogin()
                .loginProcessingUrl("/login") // 配置角色登录处理入口
                //.failureHandler(authFailHandler())          //登录失败处理跳转user登录界面
                .failureUrl("/admin/login?error=true")    //登录失败处理跳转admin登录界面
                .and()
                .logout()
                .logoutUrl("/logout")           //配置退出入口
                .logoutSuccessUrl("/logout/page")
                .deleteCookies("JSESSIONID")        //删除默认存放cookie名称-值
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(urlEntryPoint())
                .accessDeniedPage("/403");  //无权访问该页面


        //1.方便开发，csrf会拦截我所有的post请求
        http.csrf().disable();
        // 2.希望所有的默认头都具有以下自定义设置：x-frame-options允许来自同一域的任何请求，HTTP严格传输安全性（HSTS）不会添加到响应中
        http.headers().frameOptions().sameOrigin();


        //登录成功,失败跳转页面
        http.formLogin().defaultSuccessUrl("/admin/center");
    }

    /**
     * 自定义认证策略
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider()).eraseCredentials(true);
    }

    /**
     * 调用自己注入的authprovider
     * @return
     */
    @Bean
    public AuthProvider authProvider() {
        return new AuthProvider();
    }

    /**
     * 调用自己注入的LoginUrlEntryPoint
     * @return
     */
    @Bean
    public LoginUrlEntryPoint urlEntryPoint() {
        //登录失败入口
        return new LoginUrlEntryPoint("/admin/login");
    }

    /**
     * 调用自己注入的LoginAuthFailHandler
     * @return
     */
    @Bean
    public LoginAuthFailHandler authFailHandler() {

        return new LoginAuthFailHandler(urlEntryPoint());
    }

    /**
     * 调用自己注入的AuthFilter
     * 过滤器，用来验证码登录
     * @return
     */
    @Bean
    public AuthFilter authFilter() {
        AuthFilter authFilter = new AuthFilter();
        authFilter.setAuthenticationManager(authenticationManager());
        authFilter.setAuthenticationFailureHandler(authFailHandler());
        return authFilter;
    }

    /**
     * 验证码登录的时候调用
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager =  super.authenticationManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationManager;
    }



}

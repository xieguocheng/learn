package com.learn.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author： XO
 * @Description：
 * @Date： 2018/11/18 22:21
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        // super.addInterceptors(registry);
        // 较新Spring Boot的版本中这里可以直接去掉，否则会报错
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/index","/user/login", "/user/register");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * Bean Util
     * 在项目中很多时候需要把Model和DTO两个模型类来回转换,保证Model对外是隐私的,
     * 同时类似密码之类的属性也能很好地避免暴露在外了.那么ModelMapper就是为了方便转换而实现的一个
     * 类库,下面根据使用场景不断增加案例.
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}

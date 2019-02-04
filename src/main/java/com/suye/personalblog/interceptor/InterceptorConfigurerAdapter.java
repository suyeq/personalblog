package com.suye.personalblog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-03
 * Time: 16:28
 */
//拦截器配置
@Configuration
public class InterceptorConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private InterceptorAllRequestImpl interceptorAllRequest;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("进来了");
        // addPathPatterns 添加路径
        // excludePathPatterns 排除路径
        registry.addInterceptor(interceptorAllRequest).addPathPatterns("/admin/**")
                                                      .excludePathPatterns("/admin/css/**","/admin/images/**",
                                                       "/admin/js/**","/admin/plugins/**")
                                                        .excludePathPatterns("/admin/logins")
                                                        .excludePathPatterns("/admin/login");
        super.addInterceptors(registry);

    }
}

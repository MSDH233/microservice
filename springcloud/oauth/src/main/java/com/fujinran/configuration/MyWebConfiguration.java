package com.fujinran.configuration;

import com.fujinran.web.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@Configuration
public class MyWebConfiguration extends WebMvcConfigurationSupport {


    @Bean
    public HttpInterceptor httpInterceptor(){
        return new HttpInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(httpInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


//    @Bean
//    public FilterRegistrationBean tokenFilterRegistrationBean(){
//        TokenFilter tokenFilter = new TokenFilter();
//        FilterRegistrationBean<TokenFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter(tokenFilter);
//        filterFilterRegistrationBean.setOrder(Integer.MIN_VALUE);
//        //路径支持集合
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        filterFilterRegistrationBean.setName("tokenFilterRegistrationBean");
//        return filterFilterRegistrationBean ;
//    }

}
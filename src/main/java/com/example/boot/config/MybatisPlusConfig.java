package com.example.boot.config;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    /**
     * spring第三方bean MP的拦截器
     * @return MybatisPlusInterceptor MP的拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor () {

        // new MP interceptor
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

        // insert a pagination interceptor
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}

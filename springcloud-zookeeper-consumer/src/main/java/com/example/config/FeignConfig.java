package com.example.config;

import com.example.Interceptor.DemoRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * Feign客户端范围内的Bean配置，不能使用@Configuration或者@Component注解，否则会变为全局配置
 *
 * @author Raven
 */
public class FeignConfig {

    @Bean
    public DemoRequestInterceptor requestInterceptor() {
        return new DemoRequestInterceptor();
    }

}

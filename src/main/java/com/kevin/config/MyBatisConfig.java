package com.kevin.config;

import com.kevin.dao.interceptor.DaoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kevin
 */
@Configuration
public class MyBatisConfig {

    @Bean
    DaoInterceptor myInterceptor() {
        return new DaoInterceptor();
    }
}

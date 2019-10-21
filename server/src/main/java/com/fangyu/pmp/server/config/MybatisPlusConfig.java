package com.fangyu.pmp.server.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus配置
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/21 11:32 下午
 */
@Configuration
public class MybatisPlusConfig {

    // 分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    // 防SQL注入攻击
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }
}

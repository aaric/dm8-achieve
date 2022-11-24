package com.sample.dm8.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.sample.dm8.config.mybatis.DynamicTableNameHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus 配置
 *
 * @author Aaric, created on 2022-11-24T10:48.
 * @version 0.4.0-SNAPSHOT
 */
@Slf4j
@Configuration
public class MybatisPlusConfig {

    @Autowired
    private DynamicTableNameHandler dynamicTableNameHandler;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // dynamic table name
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        dynamicTableNameInnerInterceptor.setTableNameHandler(dynamicTableNameHandler);
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);

        // page
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        interceptor.addInnerInterceptor(paginationInterceptor);

        return interceptor;
    }
}

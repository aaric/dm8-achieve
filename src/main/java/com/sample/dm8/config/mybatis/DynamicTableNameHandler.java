package com.sample.dm8.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * MybatisPlus 动态表名处理器
 *
 * @author Aaric, created on 2022-11-24T14:33.
 * @version 0.4.0-SNAPSHOT
 */
@Slf4j
@Component
public class DynamicTableNameHandler implements TableNameHandler {

    private static ThreadLocal<String> tlTableName = new ThreadLocal<>();

    public static void setTableName(String tableName) {
        tlTableName.set(tableName);
    }

    @Override
    public String dynamicTableName(String sql, String tableName) {
        log.info("{} | {}-> {}", tableName, tlTableName.get(), sql);
        return StringUtils.equals("map", tableName) ? tlTableName.get() : tableName;
    }
}

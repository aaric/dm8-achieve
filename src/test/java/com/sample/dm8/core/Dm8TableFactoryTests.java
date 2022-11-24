package com.sample.dm8.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.dm8.config.mybatis.DynamicTableNameHandler;
import com.sample.dm8.dao.DynamicTableMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Dm8TableUtilsTests
 *
 * @author Aaric, created on 2022-11-22T10:30.
 * @version 0.3.0-SNAPSHOT
 */
@Disabled
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Dm8TableFactoryTests {

    @Autowired
    private DataSource dataSource;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static Dm8DbTable table;

    @Autowired
    private DynamicTableMapper dynamicTableMapper;

    static {
        List<Dm8DbColumn> columnList = new ArrayList<>();
        columnList.add(new Dm8DbColumn().setName("id").setComment("ID").setType("BIGINT")
                .setPrimaryKey(true).setDefaultValue("AUTO"));
        columnList.add(new Dm8DbColumn().setName("name").setComment("名称").setType("VARCHAR").setTypeLen("30"));
        columnList.add(new Dm8DbColumn().setName("age").setComment("年龄").setType("INT"));
        columnList.add(new Dm8DbColumn().setName("salary").setComment("薪水").setType("DOUBLE").setTypeLen("100"));
        table = new Dm8DbTable()
                .setName("test_info")
                .setComment("测试表")
                .setColumnList(columnList);
    }

    @Test
    public void testCreateTable() throws Exception {
        Dm8TableFactory factory = new Dm8TableFactory(dataSource);
        factory.dropTable(table.getName());
//        factory.createTable(table);
    }

    @Test
    public void testInsertObject() throws Exception {
        for (int i = 0; i < 50; i++) {
            int j = RandomUtils.nextInt(1, 10);
            String dataJson = "{\"name\":\"test"
                    + (i + 1)
                    + "\",\"age\":"
                    + (25 + j)
                    + ",\"salary\":"
                    + (j + 5)
                    + "000.0,\"remark\":\"todo\"}";
            Map<String, Object> dataMap = objectMapper.readValue(dataJson,
                    new TypeReference<Map<String, Object>>() {
                    });

            Dm8TableFactory factory = new Dm8TableFactory(dataSource);
            factory.insertObject(table, dataMap);
            System.err.println(dataMap);
        }
    }

    @Test
    public void testDeleteObject() throws Exception {
        Dm8TableFactory factory = new Dm8TableFactory(dataSource);
        factory.deleteObject(table, 1L);
    }

    @Test
    public void testUpdateObject() throws Exception {
        String dataJson = "{\"id\":2,\"name\":\"lisi\",\"age\": 35}";
        Map<String, Object> dataMap = objectMapper.readValue(dataJson,
                new TypeReference<Map<String, Object>>() {
                });
        Dm8TableFactory factory = new Dm8TableFactory(dataSource);
        factory.updateObject(table, dataMap);
    }

    @Test
    public void testQueryList() {
        DynamicTableNameHandler.setTableName("test_info");
        QueryWrapper<Map<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name");
        log.info("{}", dynamicTableMapper.selectList(queryWrapper));
    }

    @Test
    public void testQueryPage() {
        DynamicTableNameHandler.setTableName("test_info");
        QueryWrapper<Map<String, Object>> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name");
        IPage<Map<String, Object>> page = dynamicTableMapper.selectPage(new Page<>(1, 2), queryWrapper);
        log.info("{}", page.getTotal());
        log.info("{}", page.getRecords());
    }
}

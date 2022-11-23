package com.sample.dm8.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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

    private static Dm8DbTable table;

    static {
        List<Dm8DbColumn> columnList = new ArrayList<>();
        columnList.add(new Dm8DbColumn().setName("id").setComment("ID").setType("BIGINT")
                .setPrimaryKey(true).setDefaultValue("AUTO"));
        columnList.add(new Dm8DbColumn().setName("name").setComment("名称").setType("VARCHAR").setTypeLen("30"));
        columnList.add(new Dm8DbColumn().setName("age").setComment("年龄").setType("INT"));
        columnList.add(new Dm8DbColumn().setName("email").setComment("电子邮箱").setType("VARCHAR").setTypeLen("100"));
        table = new Dm8DbTable()
                .setName("user_info")
                .setComment("用户表")
                .setColumnList(columnList);
    }

    @Test
    public void testCreateTable() throws Exception {
        Dm8TableFactory factory = new Dm8TableFactory(dataSource);
        factory.dropTable(table.getName());
        factory.createTable(table);
    }
}

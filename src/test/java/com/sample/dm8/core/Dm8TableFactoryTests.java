package com.sample.dm8.core;

import lombok.extern.slf4j.Slf4j;
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
//@Disabled
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Dm8TableFactoryTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testCreateTable() throws Exception {
        List<Dm8DbColumn> columnList = new ArrayList<>();
        columnList.add(new Dm8DbColumn().setName("id").setType("BIGINT")
                .setPrimaryKey(true).setDefaultValue("AUTO"));
        columnList.add(new Dm8DbColumn().setName("name").setType("VARCHAR").setTypeLen("30"));
        columnList.add(new Dm8DbColumn().setName("age").setType("INT"));
        columnList.add(new Dm8DbColumn().setName("email").setType("VARCHAR").setTypeLen("100"));
        Dm8DbTable table = new Dm8DbTable()
                .setName("test")
                .setComment("测试表")
                .setColumnList(columnList);

        Dm8TableFactory factory = new Dm8TableFactory(dataSource);
        factory.dropTable(table.getName());
        factory.createTable(table);
    }
}

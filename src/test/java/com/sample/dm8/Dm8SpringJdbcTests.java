package com.sample.dm8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

/**
 * Dm8SpringJdbcTests
 *
 * @author Aaric, created on 2022-11-21T11:49.
 * @version 0.2.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Dm8SpringJdbcTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testInsert() {
        String insertSql = "INSERT INTO test (name) VALUES ('test10')";
        jdbcTemplate.execute(insertSql);
    }

    @Test
    public void testDelete() {
        String deleteSql = "DELETE FROM test WHERE name = 'test10'";
        jdbcTemplate.execute(deleteSql);
    }

    @Test
    public void testUpdate() {
        String updateSql = "UPDATE test SET name = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, new Object[]{"test22", 2});
    }

    @Test
    public void testQuery() {
        String querySql = "SELECT * FROM test WHERE name LIKE 'test%'";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(querySql);
        System.err.println(mapList);
    }
}

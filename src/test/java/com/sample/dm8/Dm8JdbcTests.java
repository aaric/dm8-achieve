package com.sample.dm8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Dm8JdbcTests
 *
 * @author Aaric, created on 2022-11-21T09:35.
 * @version 0.2.0-SNAPSHOT
 */
@Slf4j
public class Dm8JdbcTests {

    private static Connection conn;
    private static Statement state;

    @BeforeAll
    public static void setUp() throws Exception {
        Class.forName("dm.jdbc.driver.DmDriver");
        conn = DriverManager.getConnection("jdbc:dm://localhost:5236", "SYSDBA", "SYSDBA");
        conn.setAutoCommit(true);

        state = conn.createStatement();
    }

    @Test
    public void testInsert() throws Exception {
        String insertSql = "INSERT INTO test (name) VALUES ('test02')";
        Assertions.assertTrue(state.execute(insertSql));
    }

    @Test
    public void testDelete() throws Exception {
        String deleteSql = "DELETE FROM test WHERE id = 1";
        state.execute(deleteSql);
    }

    @Test
    public void testUpdate() throws Exception {
        String updateSql = "UPDATE test SET name = 'test00' WHERE id = 2";
        state.executeUpdate(updateSql);
    }

    @Test
    public void testQuery() throws Exception {
        String querySql = "SELECT * FROM test";
        ResultSet rs = state.executeQuery(querySql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.err.println(String.format("%d: %s", id, name));
        }
    }

    @AfterAll
    public static void tearDown() throws Exception {
        if (null != conn) {
            conn.close();
        }
    }
}

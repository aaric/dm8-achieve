package com.sample.dm8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * Dm8JdbcTests
 *
 * @author Aaric, created on 2022-11-21T09:35.
 * @version 0.2.0-SNAPSHOT
 */
@Disabled
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

    @AfterAll
    public static void tearDown() throws Exception {
        if (null != conn) {
            conn.close();
        }
    }

    @Test
    public void testInsert() throws Exception {
//        String insertSql = "INSERT INTO user_info (name) VALUES ('test02')";
//        state.execute(insertSql);
        String insertSql = "INSERT INTO user_info (name) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(insertSql);
        ps.setString(1, "test03");
        ps.execute();
    }

    @Test
    public void testDelete() throws Exception {
//        String deleteSql = "DELETE FROM user_info WHERE id = 1";
//        state.execute(deleteSql);
        String deleteSql = "DELETE FROM user_info WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(deleteSql);
        ps.setInt(1, 3);
        ps.execute();
    }

    @Test
    public void testUpdate() throws Exception {
//        String updateSql = "UPDATE test SET name = 'test00' WHERE id = 2";
//        state.executeUpdate(updateSql);
        String updateSql = "UPDATE user_info SET name = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(updateSql);
        ps.setString(1, "test11");
        ps.setInt(2, 2);
        ps.executeUpdate();
    }

    @Test
    public void testQuery() throws Exception {
        String querySql = "SELECT * FROM user_info";
        ResultSet rs = state.executeQuery(querySql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.err.println(String.format("%d: %s", id, name));
        }
    }
}

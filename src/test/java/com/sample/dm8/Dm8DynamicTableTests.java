package com.sample.dm8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Dm8DynamicTableTests
 *
 * @author Aaric, created on 2022-11-21T16:24.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Dm8DynamicTableTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testCreateTb() throws Exception {
        String tbName = "test";
        String tbSeqName = tbName + "_seq";
        Connection conn = dataSource.getConnection();
        Statement state = conn.createStatement();

        String tbSeqSql = "CREATE SEQUENCE "
                + tbSeqName + " "
                + "START WITH 1 INCREMENT BY 1 NOMAXVALUE "
                + "CACHE 5 NOCYCLE";
        System.err.println("tbSeqSql: " + tbSeqSql);
        state.executeUpdate(tbSeqSql);

        String tbSql = "CREATE TABLE "
                + tbName + " ( "
                + "id INT PRIMARY KEY DEFAULT " + tbSeqName + ".nextval, "
                + "name VARCHAR(30) NOT NULL "
                + ")";
        state.executeUpdate(tbSql);
    }
}

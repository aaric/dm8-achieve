package com.sample.dm8.core;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DM8 数据表工厂类
 *
 * @author Aaric, created on 2022-11-22T10:11.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
public class Dm8TableFactory {

    private DataSource dataSource;

    public Dm8TableFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static String getTableSeqName(String tbName) {
        return tbName + "_seq";
    }

    /**
     * 删除数据表
     *
     * @param tbName 表名称
     * @return
     * @throws Exception
     */
    public void dropTable(String tbName) throws Exception {
        Connection conn = dataSource.getConnection();
        Statement state = conn.createStatement();

        try {
            state.executeUpdate("DROP TABLE " + tbName);
        } catch (SQLException e) {
            log.error("no table: " + tbName, e);
        }

        try {
            state.executeUpdate("DROP SEQUENCE " + getTableSeqName(tbName));
        } catch (SQLException e) {
            log.error("no sequence: " + getTableSeqName(tbName), e);
        }

        conn.close();
    }

    /**
     * 创建数据表
     *
     * @param tbName 表名称
     * @return
     * @throws Exception
     */
    public void createTable(String tbName) throws Exception {
        createTable(tbName, true);
    }

    /**
     * 创建数据表
     *
     * @param tbName  表名称
     * @param autoInc 主键自动增长
     * @return
     * @throws Exception
     */
    public void createTable(String tbName, boolean autoInc) throws Exception {
        Connection conn = dataSource.getConnection();
        Statement state = conn.createStatement();

        log.info("createTable: {}", tbName);
        try {
            if (autoInc) {
                String tbSeqSql = "CREATE SEQUENCE "
                        + getTableSeqName(tbName) + " "
                        + "START WITH 1 INCREMENT BY 1 NOMAXVALUE "
                        + "CACHE 5 NOCYCLE";
                log.info("tbSeqSql: {}", tbSeqSql);
                state.executeUpdate(tbSeqSql);
            }

            String tbSql = "CREATE TABLE " + tbName + " ( ";

            if (autoInc) {
                tbSql += "id INT PRIMARY KEY DEFAULT " + getTableSeqName(tbName) + ".nextval, ";
            } else {
                tbSql += "id INT PRIMARY KEY, ";
            }

            tbSql += "name VARCHAR(30) NOT NULL, ";
            tbSql += "age INT, ";

            tbSql = tbSql.replaceAll(",\\s$", " ");
            tbSql += ")";
            log.info("tbSql: {}", tbSql);
            state.executeUpdate(tbSql);
        } catch (SQLException e) {
            log.error("create table exception", e);
        } finally {
            conn.close();
        }
    }
}

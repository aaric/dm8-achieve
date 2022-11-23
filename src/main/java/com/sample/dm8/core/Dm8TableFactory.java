package com.sample.dm8.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
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

        ResultSet rs = state.executeQuery(String.format("SELECT count(1) FROM user_tables WHERE table_name = '%s'",
                StringUtils.upperCase(tbName)));
        if (rs.next() && 0 < rs.getInt(1)) {
            state.executeUpdate("DROP TABLE " + tbName);
        } else {
            log.error("no table: " + tbName);
        }

        rs = state.executeQuery(String.format("SELECT count(1) FROM user_sequences WHERE sequence_name = '%s'",
                StringUtils.upperCase(getTableSeqName(tbName))));
        if (rs.next() && 0 < rs.getInt(1)) {
            state.executeUpdate("DROP SEQUENCE " + getTableSeqName(tbName));
        } else {
            log.error("no sequence: " + getTableSeqName(tbName));
        }

        conn.close();
    }

    /**
     * 创建数据表
     *
     * @param dbTable 表定义
     * @return
     * @throws Exception
     */
    public void createTable(Dm8DbTable dbTable) throws Exception {
        if (null == dbTable || StringUtils.isEmpty(dbTable.getName())
                || null == dbTable.getColumnList() || 0 == dbTable.getColumnList().size()) {
            throw new IllegalArgumentException("无效表定义");
        }

        Connection conn = dataSource.getConnection();
        Statement state = conn.createStatement();

        log.info("创建表：{}", dbTable.getName());
        try {
            // 创建序列
            String tbSeqSql = "CREATE SEQUENCE "
                    + getTableSeqName(dbTable.getName()) + " "
                    + "START WITH 1 INCREMENT BY 1 NOMAXVALUE "
                    + "CACHE 5 NOCYCLE";
            log.info("创建序列SQL: {}", tbSeqSql);
            state.executeUpdate(tbSeqSql);

            // 创建表
            String tbSql = "CREATE TABLE " + dbTable.getName() + " ( ";
            for (Dm8DbColumn col : dbTable.getColumnList()) {
                tbSql += String.format("%s ", col.getName());
                tbSql += String.format("%s%s ", col.getType(), getColumnTypeScope(col.getTypeLen()));
                if (col.isPrimaryKey()) {
                    tbSql += "PRIMARY KEY ";
                }
                if (StringUtils.isNotEmpty(col.getDefaultValue())) {
                    if (StringUtils.equals("AUTO", col.getDefaultValue())) {
                        tbSql += String.format("DEFAULT %s.nextval ", getTableSeqName(dbTable.getName()));
                    } else {
                        tbSql += String.format("NOT NULL DEFAULT %s ", col.getDefaultValue());
                    }
                }
                tbSql += ", ";
            }
            tbSql = tbSql.replaceAll(",\\s$", " ");
            tbSql += ")";
            log.info("创建表SQL：{}", tbSql);
            state.executeUpdate(tbSql);

            // 添加表注释
            state.executeUpdate(String.format("COMMENT ON TABLE %s IS '%s'",
                    dbTable.getName(), dbTable.getComment()));
            for (Dm8DbColumn col : dbTable.getColumnList()) {
                state.executeUpdate(String.format("COMMENT ON COLUMN %s.%s IS '%s'",
                        dbTable.getName(), col.getName(), col.getComment()));
            }
        } catch (SQLException e) {
            log.error("create table exception", e);
        } finally {
            conn.close();
        }
    }

    /**
     * 表序列名称
     *
     * @param tbName 表名称
     * @return
     */
    public static String getTableSeqName(String tbName) {
        return tbName + "_seq";
    }

    /**
     * 数据长度（精度或标度）
     *
     * @param length 数据长度（精度或标度）
     * @return
     */
    public static String getColumnTypeScope(String length) {
        String scope = "";
        if (StringUtils.isNotEmpty(length)) {
            scope = String.format("(%s) ", length);
        }
        return scope;
    }
}

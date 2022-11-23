package com.sample.dm8.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        try (ResultSet rs = state.executeQuery(String.format("SELECT count(1) FROM user_tables WHERE table_name = '%s'",
                StringUtils.upperCase(tbName)))) {
            if (rs.next() && 0 < rs.getInt(1)) {
                String tbSeqSql = String.format("DROP TABLE %s", tbName);
                log.info("drop sequence sql: {}", tbSeqSql);
                state.executeUpdate(tbSeqSql);
            } else {
                log.error("no table: {}", tbName);
            }
        }

        try (ResultSet rs = state.executeQuery(String.format("SELECT count(1) FROM user_sequences WHERE sequence_name = '%s'",
                StringUtils.upperCase(getTableSeqName(tbName))))) {
            if (rs.next() && 0 < rs.getInt(1)) {
                String tbSql = String.format("DROP SEQUENCE %s", getTableSeqName(tbName));
                log.info("drop table sql: {}", tbSql);
                state.executeUpdate(tbSql);
            } else {
                log.error("no sequence: {}", getTableSeqName(tbName));
            }
        }

        conn.close();
    }

    /**
     * 表序列名称
     *
     * @param tbName 表名称
     * @return
     */
    private String getTableSeqName(String tbName) {
        return tbName + "_seq";
    }

    /**
     * 数据长度（精度或标度）
     *
     * @param length 数据长度（精度或标度）
     * @return
     */
    private String getColumnTypeScope(String length) {
        String scope = "";
        if (StringUtils.isNotEmpty(length)) {
            scope = String.format("(%s) ", length);
        }
        return scope;
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
            throw new IllegalArgumentException("invalid table definition");
        }

        Connection conn = dataSource.getConnection();
        Statement state = conn.createStatement();

        log.info("create table: {}", dbTable.getName());
        try {
            // 创建序列
            String tbSeqSql = "CREATE SEQUENCE "
                    + getTableSeqName(dbTable.getName()) + " "
                    + "START WITH 1 INCREMENT BY 1 NOMAXVALUE "
                    + "CACHE 5 NOCYCLE";
            log.info("create sequence sql: {}", tbSeqSql);
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
                    if (StringUtils.equals(Dm8DbColumn.AUTO, col.getDefaultValue())) {
                        tbSql += String.format("DEFAULT %s.nextval ", getTableSeqName(dbTable.getName()));
                    } else {
                        tbSql += String.format("NOT NULL DEFAULT %s ", col.getDefaultValue());
                    }
                }
                tbSql += ", ";
            }
            tbSql = tbSql.replaceAll(",\\s$", " ");
            tbSql += ")";
            log.info("create table sql: {}", tbSql);
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
     * 新增数据
     *
     * @param table   表定义
     * @param dataMap 数据集合
     * @return
     */
    public Map<String, Object> insertObject(Dm8DbTable table, Map<String, Object> dataMap) throws SQLException {
        if (null == dataMap && 0 == dataMap.size()) {
            throw new IllegalArgumentException("invalid data map");
        }
        log.info("data map: {}", dataMap);

        List<String> keyList = new ArrayList<>();
        List<Object> valueList = new ArrayList<>();

        table.getColumnList().forEach(col -> {
            String k = col.getName();
            Object v = dataMap.get(k);
            if (null != v) {
                keyList.add(k);
                valueList.add(v instanceof String ? String.format("'%s'", v) : v);
            }
        });

        String insertSql = String.format("INSERT INTO %s (%s) VALUES (%s)", table.getName(),
                StringUtils.join(keyList, ", "), StringUtils.join(valueList, ", "));
        log.info("insert sql: {}", insertSql);

        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
        if (0 < ps.executeUpdate()) {
            String pkName = table.getPrimaryKeyName();
            if (StringUtils.isNotEmpty(pkName)) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        dataMap.put(pkName, rs.getLong(1));
                    }
                }
            }
            return dataMap;
        }

        return null;
    }
}

package com.sample.dm8.core;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * DM8 数据列
 *
 * @author Aaric, created on 2022-11-22T16:41.
 * @version 0.3.0-SNAPSHOT
 */
@Data
@Accessors(chain = true)
public class Dm8DbColumn {

    /**
     * 主键自增标识
     */
    public static final String AUTO = "AUTO";

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 数据类型：INT-整型, BIGINT-宽整型, FLOAT-浮点, DOUBLE-宽浮点, VARCHAR-字符串, DATE-日期, TIME-时间, DATATIME-日期时间, BOOL-布尔
     */
    private String type;

    /**
     * 数据长度（精度或标度）
     */
    private String typeLen;

    /**
     * 是否主键
     */
    private boolean isPrimaryKey = false;

    /**
     * 默认值：AUTO-主键自增，其他-附加"NOT NULL"
     */
    private String defaultValue;

    /**
     * 列注释
     */
    private String comment;
}

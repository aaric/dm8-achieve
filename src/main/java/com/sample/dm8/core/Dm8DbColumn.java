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
     * ID
     */
    private Long id;


    /**
     * 名称
     */
    private String name;

    /**
     * 数据类型
     */
    private Dm8DbTypeEnum dbType;

    /**
     * 精度或长度
     */
    private Integer precision;

    /**
     * 标度
     */
    private Integer scale;

    /**
     * 是否可以为空
     */
    private Boolean isNull;

    /**
     * 是否主键
     */
    private Boolean isPrimaryKey;

    /**
     * 是否唯一
     */
    private Boolean isUnique;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 列注释
     */
    private String comment;
}

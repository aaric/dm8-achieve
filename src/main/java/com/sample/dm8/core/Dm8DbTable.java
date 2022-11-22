package com.sample.dm8.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * DM8 数据表
 *
 * @author Aaric, created on 2022-11-22T16:50.
 * @version 0.3.0-SNAPSHOT
 */
@Data
@Accessors(chain = true)
public class Dm8DbTable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 数据列清单
     */
    private List<Dm8DbColumn> columnList;
}

package com.sample.dm8.core;

/**
 * DM8 数据库类型
 *
 * @author Aaric, created on 2022-11-22T16:28.
 * @version 0.3.0-SNAPSHOT
 */
public enum Dm8DbTypeEnum {

    INT,
    BIGINT,
    FLOAT,
    DOUBLE,
    VARCHAR,
    DATE,
    TIME,
    DATATIME,
    BOOL;

    Dm8DbTypeEnum of(String code) {
        Dm8DbTypeEnum type = null;
        switch (code) {
            case "INT":
                type = INT;
                break;
            case "BIGINT":
                type = BIGINT;
                break;
            case "FLOAT":
                type = FLOAT;
                break;
            case "DOUBLE":
                type = DOUBLE;
                break;
            case "VARCHAR":
                type = VARCHAR;
                break;
            case "DATE":
                type = DATE;
                break;
            case "TIME":
                type = TIME;
                break;
            case "DATATIME":
                type = DATATIME;
                break;
            case "BOOL":
                type = BOOL;
                break;
            default:
        }
        return type;
    }
}

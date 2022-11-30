package com.sample.dm8.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * DynamicTableMapper
 *
 * @author Aaric, created on 2022-11-24T10:56.
 * @version 0.4.0-SNAPSHOT
 */
public interface DynamicTableMapper extends BaseMapper<Map<String, Object>> {

    Integer selectMaxAge(@Param("tbName") String tbName);
}

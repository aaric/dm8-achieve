package com.sample.dm8.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sample.dm8.pojo.UserInfo;

/**
 * UserInfoMapper
 *
 * @author Aaric, created on 2022-11-21T14:58.
 * @version 0.2.0-SNAPSHOT
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    Integer selectMaxAge();
}

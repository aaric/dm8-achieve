package com.sample.dm8;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sample.dm8.dao.UserInfoMapper;
import com.sample.dm8.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Dm8MyBatisPlusTests
 *
 * @author Aaric, created on 2022-11-21T13:41.
 * @version 0.2.0-SNAPSHOT
 */
@Disabled
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Dm8MyBatisPlusTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testInsert() {
        String defaultName = "test01";
        UserInfo userInfo = new UserInfo()
                .setName(defaultName)
                //.setSex(SexEnum.MALE)
                .setSex(1)
                .setAge(24)
                .setEmail(defaultName + "@163.com");
        userInfoMapper.insert(userInfo);
    }

    @Test
    public void testSelectMaxAge() {
        log.info("{}", userInfoMapper.selectMaxAge());

    }

    @Test
    public void testSelectPage() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        IPage<UserInfo> page = userInfoMapper.selectPage(new Page<>(1, 2), queryWrapper);
        log.info("{}", page.getTotal());
        log.info("{}", page.getRecords());
    }
}

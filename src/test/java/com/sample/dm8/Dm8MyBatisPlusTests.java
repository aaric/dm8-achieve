package com.sample.dm8;

import com.sample.dm8.common.SexEnum;
import com.sample.dm8.dao.UserInfoMapper;
import com.sample.dm8.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
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
                .setSex(SexEnum.MALE)
                .setAge(24)
                .setEmail(defaultName + "@163.com");
        userInfoMapper.insert(userInfo);
    }

    @Test
    public void testSelectMaxAge() {
        System.err.println(userInfoMapper.selectMaxAge());
    }
}

package com.sample.dm8.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * UserInfo
 *
 * @author Aaric, created on 2022-11-21T14:55.
 * @version 0.2.0-SNAPSHOT
 */
@Data
@Accessors(chain = true)
@TableName("user_info")
public class UserInfo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    //private SexEnum sex;
    private Integer sex;

    @OrderBy
    private Integer age;

    private String email;
}

package com.example.schoolbuy.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

@Data
@TableName("user")  // 指定表名（如果表名与类名不一致）
public class User {

    @TableId(type = IdType.AUTO)  // 主键自增
    private Long id;

    private String username;
    private String password;

    @TableField("real_name")  // 数据库字段名与Java字段名不一致时指定
    private String realName;

    private String phone;
    private String email;
    private String city;

    private Gender gender;
    private String bankAccount;
    private String avatar;

    private Role role;
    private Status status;

    @TableField("personal_intro")
    private String personalIntro;

    private String wechat;

    @TableField(fill = FieldFill.INSERT)  // 插入时自动填充
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)  // 插入或更新时自动填充
    private Date updateTime;

    // 枚举类型（如果数据库存储的是字符串，MyBatis-Plus会自动转换）
    public enum Gender {
        FEMALE, MALE
    }

    public enum Role {
        USER, MERCHANT, ADMIN
    }

    public enum Status {
        PENDING, NORMAL, DISABLED
    }
}
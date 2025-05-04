package com.example.schoolbuy.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

@Data
@TableName("category")  // 指定表名
public class Category {

    @TableId(type = IdType.AUTO)  // 主键自增
    private Long id;

    private String name;

    @TableField("parent_id")  // 父分类ID（外键）
    private Long parentId;

    private Integer level;     // 分类层级
    private Integer sort;     // 排序序号

    @TableField(fill = FieldFill.INSERT)  // 插入时自动填充
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)  // 插入或更新时自动填充
    private Date updateTime;

    // 可选：用于存储子分类（非数据库字段，需排除）
//    @TableField(exist = false)
//    private List<Category> children;
}
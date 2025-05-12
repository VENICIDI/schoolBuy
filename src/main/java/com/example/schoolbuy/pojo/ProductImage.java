package com.example.schoolbuy.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("product_image")
public class ProductImage {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    // 图片URL
    @TableField("image_url")
    private String url;
    
    // 排序序号
    @TableField("sort_order")
    private Integer sort;
    
    // 是否为主图
    @TableField("is_main")
    private Boolean isMain;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
} 
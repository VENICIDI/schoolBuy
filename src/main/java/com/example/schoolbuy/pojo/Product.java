package com.example.schoolbuy.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("product")
public class Product {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("merchant_id")
    private Long merchantId;

    private String name;
    
    @TableField("category_id")
    private Long categoryId;
    
    @TableField("original_price")
    private BigDecimal originalPrice;
    
    @TableField("current_price")
    private BigDecimal currentPrice;
    
    private String description;
    
    @TableField("condition_desc")
    private String conditionDesc;
    
    private Boolean negotiable;
    
    private Integer stock;
    
    @TableField("sales_count")
    private Integer salesCount;
    
    @TableField("size_info")
    private String sizeInfo;
    
    @TableField("usage_instructions")
    private String usageInstructions;
    
    private String status;
    
    @TableField("average_rating")
    private BigDecimal averageRating;
    
    @TableField("publish_time")
    private Date publishTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // 用于排序的字段，非数据库字段
    @TableField(exist = false)
    private Integer sort;
} 
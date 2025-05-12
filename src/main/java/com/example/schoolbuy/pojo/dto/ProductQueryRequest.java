package com.example.schoolbuy.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductQueryRequest {
    
    // 分类ID
    private String categoryId;
    
    // 关键词搜索
    private String keyword;
    
    // 商品状态
    private String status;
    
    // 商品新旧程度
    private String conditionDesc;
    
    // 最低价格
    private BigDecimal minPrice;
    
    // 最高价格
    private BigDecimal maxPrice;
    
    // 是否可议价
    private Boolean negotiable;
    
    // 排序字段
    private String sortField;
    
    // 排序方向：asc/desc
    private String sortDirection;
    
    // 页码（从1开始）
    private Integer page = 1;
    
    // 每页大小
    private Integer pageSize = 10;
    
    // 商家ID
    private Long merchantId;
    
    // 最低评分
    private BigDecimal minRating;
} 
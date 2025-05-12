package com.example.schoolbuy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.schoolbuy.pojo.Product;
import com.example.schoolbuy.pojo.dto.ProductQueryRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    
    /**
     * 分页查询商品列表（自定义SQL）
     * 
     * @param page 分页参数
     * @param queryRequest 查询条件
     * @return 分页结果
     */
    IPage<Product> queryProductPage(Page<Product> page, @Param("query") ProductQueryRequest queryRequest);
    
} 
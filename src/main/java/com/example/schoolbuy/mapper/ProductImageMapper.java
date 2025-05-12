package com.example.schoolbuy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.schoolbuy.pojo.ProductImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商品图片Mapper接口
 */
@Mapper
public interface ProductImageMapper extends BaseMapper<ProductImage> {
    
    /**
     * 根据商品ID查询图片列表
     *
     * @param productId 商品ID
     * @return 图片列表
     */
    @Select("SELECT * FROM product_image WHERE product_id = #{productId} ORDER BY sort_order")
    List<ProductImage> selectByProductId(@Param("productId") Long productId);
    
} 
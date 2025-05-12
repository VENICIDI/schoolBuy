package com.example.schoolbuy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.schoolbuy.pojo.Product;
import com.example.schoolbuy.pojo.dto.PageResult;
import com.example.schoolbuy.pojo.dto.ProductQueryRequest;
import com.example.schoolbuy.pojo.dto.ProductResponse;

/**
 * 商品服务接口
 */
public interface ProductService extends IService<Product> {
    
    /**
     * 分页查询商品列表
     *
     * @param queryRequest 查询条件
     * @return 分页结果
     */
    PageResult<ProductResponse> queryProductPage(ProductQueryRequest queryRequest);
    
    /**
     * 根据ID查询商品详情
     *
     * @param id 商品ID
     * @return 商品详情
     */
    ProductResponse getProductById(Long id);
    
    /**
     * 发布商品
     *
     * @param product 商品信息
     * @return 是否成功
     */
    boolean publishProduct(Product product);
    
    /**
     * 更新商品信息
     *
     * @param product 商品信息
     * @return 是否成功
     */
    boolean updateProduct(Product product);
    
    /**
     * 删除商品
     *
     * @param id 商品ID
     * @return 是否成功
     */
    boolean deleteProduct(Long id);
} 
package com.example.schoolbuy.controller;

import com.example.schoolbuy.pojo.ApiResponse;
import com.example.schoolbuy.pojo.dto.PageResult;
import com.example.schoolbuy.pojo.dto.ProductQueryRequest;
import com.example.schoolbuy.pojo.dto.ProductResponse;
import com.example.schoolbuy.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 商品控制器
 */
@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 分页查询商品列表
     *
     * @param queryRequest 查询条件
     * @return 分页结果
     */
    @PostMapping("/category")
    public ApiResponse<PageResult<ProductResponse>> queryProductPage(@RequestBody ProductQueryRequest queryRequest) {
        log.info("分页查询商品列表: {}", queryRequest);
        PageResult<ProductResponse> pageResult = productService.queryProductPage(queryRequest);
        return ApiResponse.success(pageResult);
    }

    /**
     * 根据ID查询商品详情
     *
     * @param id 商品ID
     * @return 商品详情
     */
    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable Long id) {
        log.info("查询商品详情: id={}", id);
        ProductResponse product = productService.getProductById(id);
        return product != null ? 
                ApiResponse.success(product) : 
                ApiResponse.error(404, "商品不存在");
    }
} 
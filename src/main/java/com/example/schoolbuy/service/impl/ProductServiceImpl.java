package com.example.schoolbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.schoolbuy.mapper.CategoryMapper;
import com.example.schoolbuy.mapper.ProductImageMapper;
import com.example.schoolbuy.mapper.ProductMapper;
import com.example.schoolbuy.pojo.Category;
import com.example.schoolbuy.pojo.Product;
import com.example.schoolbuy.pojo.ProductImage;
import com.example.schoolbuy.pojo.dto.PageResult;
import com.example.schoolbuy.pojo.dto.ProductQueryRequest;
import com.example.schoolbuy.pojo.dto.ProductResponse;
import com.example.schoolbuy.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final ProductImageMapper productImageMapper;

    @Override
    public PageResult<ProductResponse> queryProductPage(ProductQueryRequest queryRequest) {
        // 1. 构建分页对象
        Page<Product> page = new Page<>(
                queryRequest.getPage() == null ? 1 : queryRequest.getPage(),
                queryRequest.getPageSize() == null ? 10 : queryRequest.getPageSize()
        );

        // 2. 构建查询条件
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        
        // 2.1 分类ID过滤
        if (StringUtils.hasText(queryRequest.getCategoryId())) {
            try {
                Long categoryId = Long.parseLong(queryRequest.getCategoryId());
                queryWrapper.eq(Product::getCategoryId, categoryId);
            } catch (NumberFormatException e) {
                // 如果分类ID不是数字，按分类名称查询
                Category category = categoryMapper.selectOne(
                        new LambdaQueryWrapper<Category>()
                                .eq(Category::getName, queryRequest.getCategoryId())
                );
                if (category != null) {
                    queryWrapper.eq(Product::getCategoryId, category.getId());
                }
            }
        }
        
        // 2.2 关键词搜索（商品名称或描述）
        if (StringUtils.hasText(queryRequest.getKeyword())) {
            queryWrapper.and(wrapper -> wrapper
                    .like(Product::getName, queryRequest.getKeyword())
                    .or()
                    .like(Product::getDescription, queryRequest.getKeyword())
            );
        }
        
        // 2.3 价格范围
        if (queryRequest.getMinPrice() != null) {
            queryWrapper.ge(Product::getCurrentPrice, queryRequest.getMinPrice());
        }
        if (queryRequest.getMaxPrice() != null) {
            queryWrapper.le(Product::getCurrentPrice, queryRequest.getMaxPrice());
        }
        
        // 2.4 是否可议价
        if (queryRequest.getNegotiable() != null) {
            queryWrapper.eq(Product::getNegotiable, queryRequest.getNegotiable());
        }
        
        // 2.5 商品状态 (默认在售)
        if (StringUtils.hasText(queryRequest.getStatus())) {
            queryWrapper.eq(Product::getStatus, queryRequest.getStatus());
        } else {
            // 默认只查询在售商品
            queryWrapper.eq(Product::getStatus, "ON_SALE");
        }
        
        // 2.6 商品新旧程度
        if (StringUtils.hasText(queryRequest.getConditionDesc())) {
            queryWrapper.eq(Product::getConditionDesc, queryRequest.getConditionDesc());
        }
        
        // 2.7 商家ID
        if (queryRequest.getMerchantId() != null) {
            queryWrapper.eq(Product::getMerchantId, queryRequest.getMerchantId());
        }
        
        // 2.8 评分筛选
        if (queryRequest.getMinRating() != null) {
            queryWrapper.ge(Product::getAverageRating, queryRequest.getMinRating());
        }
        
        // 3. 排序
        if (StringUtils.hasText(queryRequest.getSortField())) {
            String sortDirection = StringUtils.hasText(queryRequest.getSortDirection()) 
                    ? queryRequest.getSortDirection() : "desc";
            
            boolean isAsc = "asc".equalsIgnoreCase(sortDirection);
            
            switch (queryRequest.getSortField()) {
                case "currentPrice":
                    queryWrapper.orderBy(true, isAsc, Product::getCurrentPrice);
                    break;
                case "publishTime":
                    queryWrapper.orderBy(true, isAsc, Product::getPublishTime);
                    break;
                case "salesCount":
                    queryWrapper.orderBy(true, isAsc, Product::getSalesCount);
                    break;
                case "averageRating":
                    queryWrapper.orderBy(true, isAsc, Product::getAverageRating);
                    break;
                default:
                    // 默认按发布时间降序
                    queryWrapper.orderByDesc(Product::getPublishTime);
                    break;
            }
        } else {
            // 默认按发布时间降序
            queryWrapper.orderByDesc(Product::getPublishTime);
        }
        
        // 4. 执行分页查询
        IPage<Product> productPage = productMapper.selectPage(page, queryWrapper);
        
        // 5. 转换为响应对象
        List<ProductResponse> productResponses = productPage.getRecords().stream()
                .map(product -> {
                    ProductResponse response = ProductResponse.fromProduct(product);
                    
                    // 设置分类名称
                    Category category = categoryMapper.selectById(product.getCategoryId());
                    if (category != null) {
                        response.setCategoryName(category.getName());
                    }
                    
                    // 设置商品图片
                    List<ProductImage> images = productImageMapper.selectByProductId(product.getId());
                    if (images != null && !images.isEmpty()) {
                        response.setImageUrls(images.stream()
                                .sorted((a, b) -> a.getSort().compareTo(b.getSort()))
                                .map(ProductImage::getUrl)
                                .collect(Collectors.toList()));
                    } else {
                        response.setImageUrls(new ArrayList<>());
                    }
                    
                    // TODO: 设置商家信息
                    
                    return response;
                })
                .collect(Collectors.toList());
        
        // 6. 构建分页结果
        return PageResult.build(
                productResponses,
                productPage.getTotal(),
                (int) productPage.getCurrent(),
                (int) productPage.getSize()
        );
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productMapper.selectById(id);
        if (product == null) {
            return null;
        }
        
        // 无需增加访问量，新结构中没有此字段
        
        ProductResponse response = ProductResponse.fromProduct(product);
        
        // 设置分类名称
        Category category = categoryMapper.selectById(product.getCategoryId());
        if (category != null) {
            response.setCategoryName(category.getName());
        }
        
        // 设置商品图片
        List<ProductImage> images = productImageMapper.selectByProductId(product.getId());
        if (images != null && !images.isEmpty()) {
            response.setImageUrls(images.stream()
                    .sorted((a, b) -> a.getSort().compareTo(b.getSort()))
                    .map(ProductImage::getUrl)
                    .collect(Collectors.toList()));
        } else {
            response.setImageUrls(new ArrayList<>());
        }
        
        // TODO: 设置商家信息
        
        return response;
    }

    @Override
    @Transactional
    public boolean publishProduct(Product product) {
        // 设置商品状态为待审核
        product.setStatus("PENDING_APPROVAL");
        // 设置发布时间
        product.setPublishTime(new Date());
        // 初始化评分
        product.setAverageRating(new java.math.BigDecimal("5.0"));
        // 初始化销量
        product.setSalesCount(0);
        
        return save(product);
    }

    @Override
    @Transactional
    public boolean updateProduct(Product product) {
        // 判断商品是否存在
        Product existingProduct = getById(product.getId());
        if (existingProduct == null) {
            return false;
        }
        
        // 更新商品
        return updateById(product);
    }

    @Override
    @Transactional
    public boolean deleteProduct(Long id) {
        return removeById(id);
    }
} 
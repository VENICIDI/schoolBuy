package com.example.schoolbuy.pojo.dto;

import com.example.schoolbuy.pojo.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ProductResponse {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal currentPrice;
    private BigDecimal originalPrice;
    private Long categoryId;
    private String categoryName;
    private Long merchantId;
    private String merchantName;
    private String status;
    private String statusDesc;
    private String conditionDesc;
    private Boolean negotiable;
    private Integer stock;
    private Integer salesCount;
    private String sizeInfo;
    private String usageInstructions;
    private BigDecimal averageRating;
    private Date publishTime;
    private Date createTime;
    private Date updateTime;
    
    // 商品图片URL列表
    private List<String> imageUrls;
    
    // 从Product实体转换为ProductResponse
    public static ProductResponse fromProduct(Product product) {
        ProductResponse response = new ProductResponse();
        // 复制基本属性
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCurrentPrice(product.getCurrentPrice());
        response.setOriginalPrice(product.getOriginalPrice());
        response.setCategoryId(product.getCategoryId());
        response.setMerchantId(product.getMerchantId());
        response.setStatus(product.getStatus());
        response.setConditionDesc(product.getConditionDesc());
        response.setNegotiable(product.getNegotiable());
        response.setStock(product.getStock());
        response.setSalesCount(product.getSalesCount());
        response.setSizeInfo(product.getSizeInfo());
        response.setUsageInstructions(product.getUsageInstructions());
        response.setAverageRating(product.getAverageRating());
        response.setPublishTime(product.getPublishTime());
        response.setCreateTime(product.getCreateTime());
        response.setUpdateTime(product.getUpdateTime());

        // 设置状态描述
        if (product.getStatus() != null) {
            switch (product.getStatus()) {
                case "PENDING_APPROVAL": response.setStatusDesc("待审核"); break;
                case "REJECTED_RESUBMIT": response.setStatusDesc("审核不通过"); break;
                case "ON_SALE": response.setStatusDesc("在售"); break;
                case "LOCKED": response.setStatusDesc("已锁定"); break;
                case "SOLD_OUT": response.setStatusDesc("已售罄"); break;
                case "REMOVED_BY_SELLER": response.setStatusDesc("已下架"); break;
                default: response.setStatusDesc("未知状态");
            }
        }
        
        return response;
    }
} 
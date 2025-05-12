package com.example.schoolbuy.enums;

// 商品状态枚举
public enum ProductStatusEnum {
    // 待审核
    PENDING_APPROVAL,
    // 审核不通过可修改
    REJECTED_RESUBMIT,
    // 在售
    ON_SALE,
    // 已锁定（下单但未付款）
    LOCKED,
    // 已售罄
    SOLD_OUT,
    // 商家下架
    REMOVED_BY_SELLER
}

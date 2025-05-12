package com.example.schoolbuy.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    
    // 总记录数
    private Long total;
    
    // 当前页码
    private Integer currentPage;
    
    // 每页大小
    private Integer pageSize;
    
    // 总页数
    private Integer totalPages;
    
    // 当前页数据
    private List<T> records;
    
    public static <T> PageResult<T> build(List<T> records, Long total, Integer currentPage, Integer pageSize) {
        return new PageResult<>(
                total,
                currentPage,
                pageSize,
                (int) Math.ceil((double) total / pageSize),
                records
        );
    }
} 
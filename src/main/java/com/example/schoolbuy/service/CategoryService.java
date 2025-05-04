package com.example.schoolbuy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.schoolbuy.pojo.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {

    /**
     * 获取分类树形结构
     */
    List<Category> getCategoryTree();

    /**
     * 根据父ID查询子分类
     */
    List<Category> getChildrenByParentId(Long parentId);

    /**
     * 添加分类（校验名称重复）
     */
    boolean addCategory(Category category);

    /**
     * 删除分类（级联删除子分类，由数据库外键约束自动处理）
     */
    boolean deleteCategory(Long id);
}
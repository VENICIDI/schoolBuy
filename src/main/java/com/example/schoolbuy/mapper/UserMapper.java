package com.example.schoolbuy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.schoolbuy.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
} 
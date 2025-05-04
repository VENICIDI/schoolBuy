package com.example.schoolbuy.service;

import com.example.schoolbuy.pojo.User;
import com.example.schoolbuy.pojo.dto.LoginRequest;
import com.example.schoolbuy.pojo.dto.RegisterRequest;
import com.example.schoolbuy.pojo.dto.UserResponse;

public interface UserService {
    /**
     * 用户登录
     * @param loginRequest 登录请求
     * @return 登录成功的用户信息
     */
    UserResponse login(LoginRequest loginRequest);
    
    /**
     * 用户注册
     * @param registerRequest 注册请求
     * @return 注册成功的用户信息
     */
    UserResponse register(RegisterRequest registerRequest);
    
    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 用户对象
     */
    User findByUsername(String username);
} 
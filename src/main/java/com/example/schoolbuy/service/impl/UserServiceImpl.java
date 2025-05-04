package com.example.schoolbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.schoolbuy.mapper.UserMapper;
import com.example.schoolbuy.pojo.User;
import com.example.schoolbuy.pojo.dto.LoginRequest;
import com.example.schoolbuy.pojo.dto.RegisterRequest;
import com.example.schoolbuy.pojo.dto.UserResponse;
import com.example.schoolbuy.service.UserService;
import com.example.schoolbuy.utils.PasswordEncoder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse login(LoginRequest loginRequest) {
        User user = findByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        if (user.getStatus() == User.Status.DISABLED) {
            throw new RuntimeException("用户已被禁用");
        }
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        
        return UserResponse.fromUser(user);
    }

    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        // 检查用户名是否已存在v
        if (findByUsername(registerRequest.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        
        User user = new User();
        BeanUtils.copyProperties(registerRequest, user);
        
        // 加密密码
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        
        // 根据userType设置角色
        if (registerRequest.getUserType() != null) {
            if ("merchant".equalsIgnoreCase(registerRequest.getUserType())) {
                user.setRole(User.Role.MERCHANT);
            } else {
                // 默认为普通用户
                user.setRole(User.Role.USER);
            }
        } else {
            // 如果userType为空，默认为普通用户
            user.setRole(User.Role.USER);
        }
        
        user.setStatus(User.Status.NORMAL);
        
        // 设置默认头像
        // user.setAvatar("/default-avatar.png");

        // 时间字段会自动填充
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        
        // 保存用户
        save(user);
        
        return UserResponse.fromUser(user);
    }

    @Override
    public User findByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return getOne(queryWrapper);
    }
} 
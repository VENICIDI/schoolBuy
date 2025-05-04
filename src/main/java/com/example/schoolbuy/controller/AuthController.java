package com.example.schoolbuy.controller;

import com.example.schoolbuy.pojo.ApiResponse;
import com.example.schoolbuy.pojo.dto.LoginRequest;
import com.example.schoolbuy.pojo.dto.RegisterRequest;
import com.example.schoolbuy.pojo.dto.UserResponse;
import com.example.schoolbuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     * @param loginRequest 登录请求
     * @return 包含用户信息的响应
     */
    @PostMapping("/login")
    public ApiResponse<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserResponse userResponse = userService.login(loginRequest);
            return ApiResponse.success("登录成功", userResponse);
        } catch (Exception e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }

    /**
     * 用户注册
     * @param registerRequest 注册请求
     * @return 包含新用户信息的响应
     */
    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody RegisterRequest registerRequest) {

        System.out.println(registerRequest);

        try {
            UserResponse userResponse = userService.register(registerRequest);
            return ApiResponse.success("注册成功", userResponse);
        } catch (Exception e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }
}
package com.example.schoolbuy.pojo.dto;

import com.example.schoolbuy.pojo.User.Gender;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String realName;
    private String phone;
    private String email;
    private String city;
    private Gender gender;
    private String wechat;
    private String bankAccount;
    private String userType;
} 
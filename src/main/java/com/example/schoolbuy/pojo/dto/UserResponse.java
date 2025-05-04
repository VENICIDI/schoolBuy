package com.example.schoolbuy.pojo.dto;

import com.example.schoolbuy.pojo.User;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String realName;
    private String phone;
    private String email;
    private String city;
    private User.Gender gender;
    private String avatar;
    private User.Role role;
    private User.Status status;
    private String personalIntro;
    private String wechat;
    private String bankAccount;
    
    public static UserResponse fromUser(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRealName(user.getRealName());
        response.setPhone(user.getPhone());
        response.setEmail(user.getEmail());
        response.setCity(user.getCity());
        response.setGender(user.getGender());
        response.setAvatar(user.getAvatar());
        response.setRole(user.getRole());
        response.setStatus(user.getStatus());
        response.setPersonalIntro(user.getPersonalIntro());
        response.setWechat(user.getWechat());
        response.setBankAccount(user.getBankAccount());
        return response;
    }
} 
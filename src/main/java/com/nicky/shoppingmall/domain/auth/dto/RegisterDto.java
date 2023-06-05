package com.nicky.shoppingmall.domain.auth.dto;

import lombok.Builder;

@Builder
public class RegisterDto {
    private int id; // useGeneratedKeys
    private String email;
    private String nickname;
    private String password;
    private int type;
    private String authorityCode;
    private int status;
    private Boolean isEmailAlertConfirm;
    private String phone;

    public int getId() {return id;}
}

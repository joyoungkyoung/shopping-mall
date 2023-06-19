package com.nicky.shoppingmall.domain.adminMng.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReqCreateAdminUser extends BusinessDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String authorityCode;

    @Override
    public String invalidBlank() {
        if(username.startsWith(" ") || username.endsWith(" ")) {
            return "username";
        }
        if(password.startsWith(" ") || password.endsWith(" ")) {
            return "password";
        }
        if(nickname.startsWith(" ") || nickname.endsWith(" ")) {
            return "nickname";
        }
        if(authorityCode.startsWith(" ") || authorityCode.endsWith(" ")) {
            return "authorityCode";
        }
        return null;
    }
}

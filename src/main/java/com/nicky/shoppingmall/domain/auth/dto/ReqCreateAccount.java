package com.nicky.shoppingmall.domain.auth.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReqCreateAccount extends BusinessDto{
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    @NotNull
    private Boolean isEmailAlertConfirm;
    @NotBlank
    private String phone;
    // 주소지
    @NotBlank
    private String zipCode;
    @NotBlank
    private String addressMain;
    @NotBlank
    private String addressSub;

    @Override
    public String invalidBlank() {
        if(email.startsWith(" ") || email.endsWith(" ")) {
            return "email";
        }
        if(password.startsWith(" ") || password.endsWith(" ")) {
            return "password";
        }
        if(nickname.startsWith(" ") || nickname.endsWith(" ")) {
            return "nickname";
        }
        if(phone.startsWith(" ") || phone.endsWith(" ")) {
            return "phone";
        }
        if(zipCode.startsWith(" ") || zipCode.endsWith(" ")) {
            return "zipCode";
        }
        if(addressMain.startsWith(" ") || addressMain.endsWith(" ")) {
            return "addressMain";
        }
        if(addressSub.startsWith(" ") || addressSub.endsWith(" ")) {
            return "addressSub";
        }
     
        return null;
    }
}

package com.nicky.shoppingmall.domain.auth.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReqCreateAccount extends BusinessDto{
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    @Override
    public String invalidBlank() {
        if(email.startsWith(" ") || email.endsWith(" ")) {
            return "email";
        }
        if(password.startsWith(" ") || password.endsWith(" ")) {
            return "password";
        }
        return null;
    }
}

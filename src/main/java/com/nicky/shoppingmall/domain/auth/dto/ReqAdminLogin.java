package com.nicky.shoppingmall.domain.auth.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReqAdminLogin extends BusinessDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @Override
    public String invalidBlank() {
        if(username.startsWith(" ") || username.endsWith(" ")) {
            return "username";
        }
        if(password.startsWith(" ") || password.endsWith(" ")) {
            return "password";
        }
        return null;
    }
}

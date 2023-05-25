package com.nicky.shoppingmall.domain.auth.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReqLogin extends BusinessDto{
    @NotBlank
    public String email;
    @NotBlank
    public String password;

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

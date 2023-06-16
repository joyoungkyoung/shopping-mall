package com.nicky.shoppingmall.domain.adminMng.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReqDuplUsername extends BusinessDto {
    @NotBlank
    private String username;

    @Override
    public String invalidBlank() {
        if(username.startsWith(" ") || username.endsWith(" ")) {
            return "username";
        }
        return null;
    }
}

package com.nicky.shoppingmall.domain.adminMng.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReqModifyAdminUser extends BusinessDto {
    @NotNull
    private Integer id;
    @NotBlank
    private String nickname;
    @NotBlank
    private String authorityCode;
    
    @Override
    public String invalidBlank() {
        if(nickname.startsWith("") || nickname.endsWith("")) {
            return "nickname";
        }
        if(authorityCode.startsWith("") || authorityCode.endsWith("")) {
            return "authorityCode";
        }
        return null;
    }
}
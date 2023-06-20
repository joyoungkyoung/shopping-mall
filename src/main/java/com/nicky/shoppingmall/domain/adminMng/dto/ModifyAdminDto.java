package com.nicky.shoppingmall.domain.adminMng.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ModifyAdminDto {
    private Integer id;
    private String nickname;
    private String authorityCode;

    public ModifyAdminDto(Integer id, String nickname, String authorityCode) {
        this.id = id;
        this.nickname = nickname;
        this.authorityCode = authorityCode;
    }

    @Getter
    public static class Request extends BusinessDto{
         @NotNull
        private Integer id;
        @NotBlank
        private String nickname;
        @NotBlank
        private String authorityCode;
        
        @Override
        public String invalidBlank() {
            if(isBlank(nickname)) return "nickname";
            if(isBlank(authorityCode)) return "authorityCode";
            
            return null;
        }

    }
}

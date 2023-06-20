package com.nicky.shoppingmall.domain.adminMng.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class CreateAdminDto {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String authorityCode;

    @Builder
    public CreateAdminDto(String username, String password, String nickname, String authorityCode) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.authorityCode = authorityCode;
    }

    public Integer getId() { return id; }

    @Getter
    public static class Request extends BusinessDto{
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
            if(isBlank(username)) return "username";
            if(isBlank(password)) return "password";
            if(isBlank(nickname)) return "nickname";
            if(isBlank(authorityCode)) return "authorityCode";

            return null;
        }

    }
}

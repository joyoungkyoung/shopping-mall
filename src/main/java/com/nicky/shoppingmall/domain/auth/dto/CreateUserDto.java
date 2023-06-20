package com.nicky.shoppingmall.domain.auth.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
public class CreateUserDto {
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

    @Getter
    public static class Request extends BusinessDto {
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
            if(isBlank(email)) return "email";
            if(isBlank(password)) return "password";
            if(isBlank(nickname)) return "nickname";
            if(isBlank(phone)) return "phone";
            if(isBlank(zipCode)) return "zipCode";
            if(isBlank(addressMain)) return "addressMain";
            if(isBlank(addressSub)) return "addressSub";
        
            return null;
        }
    }
}

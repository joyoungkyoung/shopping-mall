package com.nicky.shoppingmall.domain.auth.dto;

import com.nicky.shoppingmall.config.business.BusinessDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class AdminLoginDto {
    @Getter
    public static class Request extends BusinessDto {
        @NotBlank
        private String username;
        @NotBlank
        private String password;

        @Override
        public String invalidBlank() {
            if(isBlank(username)) return "username";
            if(isBlank(password)) return "password";

            return null;
        }
    }

    @Getter
    public static class Response {
        private String accessToken;
        private String refreshToken;

        public Response(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }
}

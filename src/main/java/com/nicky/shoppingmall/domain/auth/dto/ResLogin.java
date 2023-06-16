package com.nicky.shoppingmall.domain.auth.dto;

import lombok.Getter;

@Getter
public class ResLogin {
    private String accessToken;
    private String refreshToken;

    public ResLogin(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

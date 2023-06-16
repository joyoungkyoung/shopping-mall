package com.nicky.shoppingmall.domain.auth.dto;

public class ChangeRefreshTokenDto {
    private String username;
    private String refreshToken;

    public ChangeRefreshTokenDto(String username, String refreshToken) {
        this.username = username;
        this.refreshToken = refreshToken;
    }
}

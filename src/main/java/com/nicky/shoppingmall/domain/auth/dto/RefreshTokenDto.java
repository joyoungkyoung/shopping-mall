package com.nicky.shoppingmall.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenDto {
    private String username;

    public static class Change {
        private String username;
        private String refreshToken;

        public Change(String username, String refreshToken) {
            this.username = username;
            this.refreshToken = refreshToken;
        }
    }
}

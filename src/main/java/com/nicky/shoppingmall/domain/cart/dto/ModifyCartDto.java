package com.nicky.shoppingmall.domain.cart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ModifyCartDto {
    private Integer id;
    private Integer userId;
    private Integer cnt;

    public ModifyCartDto(Integer id, Integer userId, Integer cnt) {
        this.id = id;
        this.userId = userId;
        this.cnt = cnt;
    }

    @Getter
    public static class Request {
        @NotNull
        private Integer id;
        @NotNull
        private Integer cnt;
    }

    @Getter
    public static class Response {
        private Integer cnt;

        public Response(Integer cnt) {
            this.cnt = cnt;
        }
    }
}

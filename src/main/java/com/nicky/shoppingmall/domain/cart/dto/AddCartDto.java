package com.nicky.shoppingmall.domain.cart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
public class AddCartDto {
    private Integer userId;
    private Integer productId;
    private Integer productOptionId;
    private Integer cnt; 

    @Getter
    public static class Request {
        @NotNull
        private Integer productId;
        private Integer productOptionId;
        @NotNull
        private Integer cnt;
    }
}

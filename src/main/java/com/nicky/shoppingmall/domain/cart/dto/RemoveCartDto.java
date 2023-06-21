package com.nicky.shoppingmall.domain.cart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class RemoveCartDto {
    private Integer id;
    private Integer userId;

    public RemoveCartDto(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }

    @Getter
    public static class Request {
        @NotNull
        private Integer id;
    }
}

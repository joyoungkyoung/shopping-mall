package com.nicky.shoppingmall.domain.product.dto;

import java.util.List;

import lombok.Getter;

public class RemoveProductDto {

    @Getter
    public static class Request {
        private List<Integer> productIdList;
    }
}

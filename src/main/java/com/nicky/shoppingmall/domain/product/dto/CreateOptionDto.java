package com.nicky.shoppingmall.domain.product.dto;

import lombok.Getter;

@Getter
public class CreateOptionDto {
    private int productId;
    private String optName;
    private String optValue;

    public CreateOptionDto(Integer productId, String optName, String optValue) {
        this.productId = productId;
        this.optName = optName;
        this.optValue = optValue;
    }
}

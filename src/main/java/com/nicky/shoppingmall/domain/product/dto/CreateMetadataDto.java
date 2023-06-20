package com.nicky.shoppingmall.domain.product.dto;

import lombok.Getter;

@Getter
public class CreateMetadataDto {
    private Integer productId;
    private String fieldName;
    private String fieldValue;

    public CreateMetadataDto(Integer productId, String fieldName, String fieldValue) {
        this.productId = productId;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}

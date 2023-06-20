package com.nicky.shoppingmall.domain.product.dto;

import lombok.Getter;

@Getter
public class CreateSubImageDto {
    private Integer productId;
    private Integer imageId;
    private Boolean isMain = false; 

    public CreateSubImageDto(Integer productId, Integer imageId, Boolean isMain) {
        this.productId = productId;
        this.imageId = imageId;
        this.isMain = isMain;
    }
}

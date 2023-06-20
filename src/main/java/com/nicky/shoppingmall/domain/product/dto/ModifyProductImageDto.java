package com.nicky.shoppingmall.domain.product.dto;

public class ModifyProductImageDto {
    private Integer productId;
    private Integer mainImageId;
    private Integer thumbImageId;

    public ModifyProductImageDto(Integer productId, Integer mainImageId, Integer thumbImageId) {
        this.productId = productId;
        this.mainImageId = mainImageId;
        this.thumbImageId = thumbImageId;
    }
}

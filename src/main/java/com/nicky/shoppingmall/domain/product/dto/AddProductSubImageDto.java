package com.nicky.shoppingmall.domain.product.dto;

public class AddProductSubImageDto {
    private Integer productId;
    private Integer imageId;
    private Boolean isMain;
    private Integer order;

    public AddProductSubImageDto(Integer productId, Integer imageId, Boolean isMain, Integer order) {
        this.productId = productId;
        this.imageId = imageId;
        this.isMain = isMain;
        this.order = order;
    }
}

package com.nicky.shoppingmall.domain.product.dto;

public class GetProductDto {
    private String imageHost;
    private Integer productId;

    public GetProductDto(String imageHost) {
        this.imageHost = imageHost;
    }

    public GetProductDto(String imageHost, Integer productId) {
        this(imageHost);
        this.productId = productId;
    }
}

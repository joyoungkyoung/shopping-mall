package com.nicky.shoppingmall.domain.product.dto;

import lombok.Getter;

@Getter
public class ProductImageDto {
    private Integer id;
    private Integer imageId;
    private String url;
    private Boolean isMain;
    private Integer order;
}

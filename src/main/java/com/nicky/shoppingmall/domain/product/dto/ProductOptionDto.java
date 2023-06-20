package com.nicky.shoppingmall.domain.product.dto;

import java.time.Instant;

import lombok.Getter;

@Getter
public class ProductOptionDto {
    private Integer id;
    private Integer productId;
    private String optName;
    private String optValue;
    private Instant createDate;
}

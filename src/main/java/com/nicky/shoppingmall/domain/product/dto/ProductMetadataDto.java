package com.nicky.shoppingmall.domain.product.dto;

import java.time.Instant;

import lombok.Getter;

@Getter
public class ProductMetadataDto {
    private Integer id;
    private Integer productId;
    private String fieldName;
    private String fieldValue;
    private Instant createDate;
}

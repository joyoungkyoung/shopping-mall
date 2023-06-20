package com.nicky.shoppingmall.domain.product.dto;

import java.time.Instant;

import lombok.Getter;

@Getter
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private String mainImageUrl;
    private Integer ConsumerPrice;
    private Integer ProductPrice;
    private Boolean isShow;
    private Integer stock;
    private Instant createDate;
    private Instant updateDate;
    private Instant deleteDate;
}

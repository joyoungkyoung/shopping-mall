package com.nicky.shoppingmall.domain.product.dto.response;

import java.time.Instant;

import lombok.Getter;

@Getter
public class ResGetProduct {
    private Integer id;
    private String name;
    private String description;
    private Integer thumbImageId;
    private String thumbImageUrl;
    private Integer cunsumerPrice;
    private Integer productPrice;
    private Boolean isShow;
    private Integer stock;
    private Integer favoriteCnt;
    private Instant createDate;
    private Instant updateDate;
    private Instant deleteDate;
}

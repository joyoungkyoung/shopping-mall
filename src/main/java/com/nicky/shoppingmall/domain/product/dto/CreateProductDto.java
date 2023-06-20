package com.nicky.shoppingmall.domain.product.dto;

import lombok.Builder;

public class CreateProductDto {
    private Integer id;
    private Integer categoryId;
    private Integer discountId;
    private String name;
    private String description; // html
    private Integer mainImageId;
    private Integer thumbImageId;
    private Integer supplyPrice;
    private Integer consumerPrice;
    private Integer productPrice;
    private Integer tax;
    private String tag;
    private Integer margin;
    private Integer stock = 0;

    @Builder
    public CreateProductDto(Integer categoryId, Integer discountId, String name, String description, Integer mainImageId, Integer thumbImageId, Integer supplyPrice, Integer consumerPrice, Integer productPrice, Integer tax, Integer margin, String tag, Integer stock) {
        this.categoryId = categoryId;
        this.discountId = discountId;
        this.name = name;
        this.description = description;
        this.mainImageId = mainImageId;
        this.thumbImageId = thumbImageId;
        this.supplyPrice = supplyPrice;
        this.consumerPrice = consumerPrice;
        this.productPrice = productPrice;
        this.tax = tax;
        this.margin = margin;
        this.tag = tag;
        this.stock = stock;
    }

    public Integer getId() { return id; }
}

package com.nicky.shoppingmall.domain.category.dto;

public class ChangeCategoryDto {
    private Integer categoryId;
    private String name;

    public ChangeCategoryDto(Integer categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
}

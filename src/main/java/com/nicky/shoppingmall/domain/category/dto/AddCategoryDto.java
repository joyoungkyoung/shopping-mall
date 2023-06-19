package com.nicky.shoppingmall.domain.category.dto;

public class AddCategoryDto {
    private Integer parentCategoryId;
    private String name;
    private Integer order;

    public AddCategoryDto(Integer parentCategoryId, String name, Integer order) {
        this.parentCategoryId = parentCategoryId;
        this.name = name;
        this.order = order;
    }
}

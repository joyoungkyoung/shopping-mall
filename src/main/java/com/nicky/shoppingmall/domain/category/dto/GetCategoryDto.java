package com.nicky.shoppingmall.domain.category.dto;

import lombok.Getter;

@Getter
public class GetCategoryDto {
    private Integer id;
    private Integer parentCategoryId;
    private String name;
    private Integer order;
}

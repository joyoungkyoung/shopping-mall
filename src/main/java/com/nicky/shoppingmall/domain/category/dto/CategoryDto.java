package com.nicky.shoppingmall.domain.category.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CategoryDto {

    @Getter
    public static class Data {
        private Integer id;
        private Integer parentCategoryId;
        private String name;
        private Integer order;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private List<Data> list;
    }
}

package com.nicky.shoppingmall.domain.category.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChangeCategoryDto {
    private Integer categoryId;
    private String name;

    public ChangeCategoryDto(Integer categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    @Getter
    public static class Request {
        @Getter
        @NoArgsConstructor
        public static class Category {
            private Integer categoryId;
            private String name;
        }
        
        @NotNull
        private List<Category> categoryList;
    }
}

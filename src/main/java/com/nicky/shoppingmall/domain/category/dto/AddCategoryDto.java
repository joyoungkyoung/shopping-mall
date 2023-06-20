package com.nicky.shoppingmall.domain.category.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AddCategoryDto {
    private Integer parentCategoryId;
    private String name;
    private Integer order;

    public AddCategoryDto(Integer parentCategoryId, String name, Integer order) {
        this.parentCategoryId = parentCategoryId;
        this.name = name;
        this.order = order;
    }

    @Getter
    public static class Request {
        @Getter
        @NoArgsConstructor
        public static class Category {
            private Integer parentCategoryId;
            private String name;
        }
        
        @NotNull
        private List<Category> categoryList;
    }
}

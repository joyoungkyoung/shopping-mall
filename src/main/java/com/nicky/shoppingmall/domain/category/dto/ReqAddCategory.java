package com.nicky.shoppingmall.domain.category.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ReqAddCategory {
    @Getter
    @NoArgsConstructor
    public static class Category {
        private Integer parentCategoryId;
        private String name;
    }
    
    @NotNull
    private List<Category> categoryList;
}

package com.nicky.shoppingmall.domain.category.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ReqChangeCategory {
    @Getter
    @NoArgsConstructor
    public static class Category {
        private Integer categoryId;
        private String name;
    }
    
    @NotNull
    private List<Category> categoryList;
}

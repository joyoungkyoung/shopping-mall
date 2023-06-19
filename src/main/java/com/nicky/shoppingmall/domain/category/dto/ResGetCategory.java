package com.nicky.shoppingmall.domain.category.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResGetCategory {

    @Getter
    @NoArgsConstructor
    public static class Category {
        private Integer id;
        private String name;
    }
    
    private Integer id;
    private List<Category> children;
}

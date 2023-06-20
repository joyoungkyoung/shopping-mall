package com.nicky.shoppingmall.domain.category.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class RemoveCategory {
    
    @Getter
    public static class Request {
        @NotNull
        private List<Integer> categoryIdList;
    }
    
}

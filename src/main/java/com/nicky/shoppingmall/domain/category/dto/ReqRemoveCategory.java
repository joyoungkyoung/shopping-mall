package com.nicky.shoppingmall.domain.category.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReqRemoveCategory {
    @NotNull
    private List<Integer> categoryIdList;
}

package com.nicky.shoppingmall.domain.product.dto.request;

import java.util.List;

import lombok.Getter;

@Getter
public class ReqRemoveProductList {
    private List<Integer> productIdList;
}

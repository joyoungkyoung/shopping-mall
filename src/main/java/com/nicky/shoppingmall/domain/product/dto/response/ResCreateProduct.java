package com.nicky.shoppingmall.domain.product.dto.response;

import lombok.Getter;

@Getter
public class ResCreateProduct {
    private Integer id;  

    public ResCreateProduct(Integer id) {
        this.id = id;
    }
}

package com.nicky.shoppingmall.domain.product.dto;

import java.time.Instant;

import lombok.Getter;

@Getter
public class ProductOptionDto {
    private Integer id;
    private Integer productId;
    private String optName;
    private String optValue;
    private Instant createDate;

    public static class Insert {
        private int productId;
        private String optName;
        private String optValue;

        public Insert(Integer productId, String optName, String optValue) {
            this.productId = productId;
            this.optName = optName;
            this.optValue = optValue;
        }
    }
}

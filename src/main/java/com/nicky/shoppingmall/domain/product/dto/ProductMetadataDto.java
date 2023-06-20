package com.nicky.shoppingmall.domain.product.dto;

import java.time.Instant;

import lombok.Getter;

@Getter
public class ProductMetadataDto {
    private Integer id;
    private Integer productId;
    private String fieldName;
    private String fieldValue;
    private Instant createDate;

    public static class Insert {
        private Integer productId;
        private String fieldName;
        private String fieldValue;

        public Insert(Integer productId, String fieldName, String fieldValue) {
            this.productId = productId;
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
        }
    }
}

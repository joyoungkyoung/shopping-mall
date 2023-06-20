package com.nicky.shoppingmall.domain.product.dto;

import lombok.Getter;

@Getter
public class ProductImageDto {
    private Integer id;
    private Integer imageId;
    private String url;
    private Boolean isMain;
    private Integer order;

    public static class Insert {
        private Integer productId;
        private Integer imageId;
        private Boolean isMain;
        private Integer order;

        public Insert(Integer productId, Integer imageId, Boolean isMain, Integer order) {
            this.productId = productId;
            this.imageId = imageId;
            this.isMain = isMain;
            this.order = order;
        }
    }
}

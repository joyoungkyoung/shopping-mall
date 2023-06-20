package com.nicky.shoppingmall.domain.product.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ProductListDto {
    
    @Getter
    public static class Data {
        private Integer id;
        private String name;
        private String description;
        private Integer thumbImageId;
        private String thumbImageUrl;
        private Integer cunsumerPrice;
        private Integer productPrice;
        private Boolean isShow;
        private Integer stock;
        private Integer favoriteCnt;
        private Instant createDate;
        private Instant updateDate;
        private Instant deleteDate;
    }

    public static class Get {
        private String imageHost;

        public Get(String imageHost) {
            this.imageHost = imageHost;
        }
    }


    @Getter
    @AllArgsConstructor
    public static class Response {
        List<Data> list;
    }
}

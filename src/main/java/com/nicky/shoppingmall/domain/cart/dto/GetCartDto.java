package com.nicky.shoppingmall.domain.cart.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class GetCartDto {
    @Getter
    public static class Data {
        private Integer id;
        private Integer cnt;
        private Integer productId;
        private String productName;
        private Integer consumerPrice;
        private Integer discountPercent;
        private Integer discountPrice;
        private Integer productOptionId;
        private String productOptionName;
        private String productOptionValue;
    }
    

    @Getter
    @AllArgsConstructor
    public static class Resonse {
        private List<Data> list;
    }
}

package com.nicky.shoppingmall.domain.product.dto;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Getter;


public class ProductDto {

    @Getter
    public static class Data {
        private Integer id;
        private String name;
        private String description;
        private String mainImageUrl;
        private Integer ConsumerPrice;
        private Integer ProductPrice;
        private Boolean isShow;
        private Integer stock;
        private Instant createDate;
        private Instant updateDate;
        private Instant deleteDate;
    }

    public static class Get {
        private String imageHost;
        private Integer productId;

        public Get(String imageHost, Integer productId) {
            this.imageHost = imageHost;
            this.productId = productId;
        }
    }

    @Builder
    public static class Update {
        private Integer productId;
        private Integer mainImageId;
        private Integer thumbImageId;
    }
    
    @Getter
    public static class Response {
        private Integer id;
        private String name;
        private String description;
        private String mainImageUrl;
        private Integer consumerPrice;
        private Integer productPrice;
        private Boolean isShow;
        private Integer stock;
        private Instant createDate;
        private Instant updateDate;
        private Instant deleteDate;
        
        private List<ProductImageDto> subImageList;
        private List<ProductMetadataDto> metadataList;
        private List<ProductOptionDto> optionList;

        public Response(Data dto) {
            this.id = dto.getId();
            this.name = dto.getName();
            this.description = dto.getDescription();
            this.mainImageUrl = dto.getMainImageUrl();
            this.consumerPrice = dto.getConsumerPrice();
            this.productPrice = dto.getProductPrice();
            this.isShow = dto.getIsShow();
            this.stock = dto.getStock();
            this.createDate = dto.getCreateDate();
            this.updateDate = dto.getUpdateDate();
            this.deleteDate = dto.getDeleteDate();
        }

        public void setMetadataList(List<ProductMetadataDto> metadataList){
            this.metadataList = metadataList;
        }
        
        public void setSubImageList(List<ProductImageDto> subImageList){
            this.subImageList = subImageList;
        }
        
        public void setOptionList(List<ProductOptionDto> optionList){
            this.optionList = optionList;
        }
    }
}

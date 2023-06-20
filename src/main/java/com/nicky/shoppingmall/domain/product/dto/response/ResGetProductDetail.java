package com.nicky.shoppingmall.domain.product.dto.response;

import java.time.Instant;
import java.util.List;

import com.nicky.shoppingmall.domain.product.dto.ProductDto;
import com.nicky.shoppingmall.domain.product.dto.ProductImageDto;
import com.nicky.shoppingmall.domain.product.dto.ProductMetadataDto;
import com.nicky.shoppingmall.domain.product.dto.ProductOptionDto;

import lombok.Getter;

@Getter
public class ResGetProductDetail {
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

    public ResGetProductDetail(ProductDto dto) {
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

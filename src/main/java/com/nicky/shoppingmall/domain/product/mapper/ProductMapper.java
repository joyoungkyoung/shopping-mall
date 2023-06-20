package com.nicky.shoppingmall.domain.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nicky.shoppingmall.domain.product.dto.CreateProductDto;
import com.nicky.shoppingmall.domain.product.dto.ProductDto;
import com.nicky.shoppingmall.domain.product.dto.ProductImageDto;
import com.nicky.shoppingmall.domain.product.dto.ProductListDto;
import com.nicky.shoppingmall.domain.product.dto.ProductMetadataDto;
import com.nicky.shoppingmall.domain.product.dto.ProductOptionDto;


/**
 * prefix : get, create, remove, modify, change
 */
@Mapper
public interface ProductMapper {
    // 상품 조회
    public List<ProductListDto.Data> getProductList(ProductListDto.Get dto);
    public ProductDto.Data getProduct(ProductDto.Get dto);
    public List<ProductImageDto> getProductImageList(ProductDto.Get dto);
    public List<ProductMetadataDto> getProductMetadataList(Integer productId);
    public List<ProductOptionDto> getProductOptionList(Integer productId);
    

    // 상품 등록
    public void createProduct(CreateProductDto dto);
    public void createMetadataList(List<ProductMetadataDto.Insert> dtoList);
    public void createOptionList(List<ProductOptionDto.Insert> dtoList);

    public void modifyProductImage(ProductDto.Update dto);
    public void removeProductSubImageList(Integer productId);
    public void addProductSubImageList(List<ProductImageDto.Insert> dto);
    
    // 상품 활성화, 체크
    public Boolean isEnableProcess(Integer productId);
    public void enableProduct(Integer productId);

    // 상품 삭제
    public void removeProductList(List<Integer> productIdList);
}

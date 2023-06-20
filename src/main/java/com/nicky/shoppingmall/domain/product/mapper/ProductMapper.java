package com.nicky.shoppingmall.domain.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nicky.shoppingmall.domain.product.dto.AddProductSubImageDto;
import com.nicky.shoppingmall.domain.product.dto.CreateMetadataDto;
import com.nicky.shoppingmall.domain.product.dto.CreateOptionDto;
import com.nicky.shoppingmall.domain.product.dto.CreateProductDto;
import com.nicky.shoppingmall.domain.product.dto.GetProductDto;
import com.nicky.shoppingmall.domain.product.dto.ModifyProductImageDto;
import com.nicky.shoppingmall.domain.product.dto.ProductDto;
import com.nicky.shoppingmall.domain.product.dto.ProductImageDto;
import com.nicky.shoppingmall.domain.product.dto.ProductMetadataDto;
import com.nicky.shoppingmall.domain.product.dto.ProductOptionDto;
import com.nicky.shoppingmall.domain.product.dto.response.ResGetProduct;


/**
 * prefix : get, create, remove, modify, change
 */
@Mapper
public interface ProductMapper {
    // 상품 조회
    public List<ResGetProduct> getProductList(GetProductDto dto);
    public ProductDto getProduct(GetProductDto dto);
    public List<ProductImageDto> getProductImageList(GetProductDto dto);
    public List<ProductMetadataDto> getProductMetadataList(Integer productId);
    public List<ProductOptionDto> getProductOptionList(Integer productId);
    

    // 상품 등록
    public void createProduct(CreateProductDto dto);
    public void createMetadataList(List<CreateMetadataDto> dtoList);
    public void createOptionList(List<CreateOptionDto> dtoList);

    public void modifyProductImage(ModifyProductImageDto dto);
    public void removeProductSubImageList(Integer productId);
    public void addProductSubImageList(List<AddProductSubImageDto> dto);
    
    // 상품 활성화, 체크
    public Boolean isEnableProcess(Integer productId);
    public void enableProduct(Integer productId);

    // 상품 삭제
    public void removeProductList(List<Integer> productIdList);
}

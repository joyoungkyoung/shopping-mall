package com.nicky.shoppingmall.domain.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.business.BusinessException;
import com.nicky.shoppingmall.config.error.ErrorInfo;
import com.nicky.shoppingmall.config.util.CommonUtil;
import com.nicky.shoppingmall.domain.product.dto.AddProductSubImageDto;
import com.nicky.shoppingmall.domain.product.dto.CreateMetadataDto;
import com.nicky.shoppingmall.domain.product.dto.CreateOptionDto;
import com.nicky.shoppingmall.domain.product.dto.CreateProductDto;
import com.nicky.shoppingmall.domain.product.dto.GetProductDto;
import com.nicky.shoppingmall.domain.product.dto.ModifyProductImageDto;
import com.nicky.shoppingmall.domain.product.dto.ProductDto;
import com.nicky.shoppingmall.domain.product.dto.request.ReqCreateProduct;
import com.nicky.shoppingmall.domain.product.dto.request.ReqCreateProductComplete;
import com.nicky.shoppingmall.domain.product.dto.request.ReqRemoveProductList;
import com.nicky.shoppingmall.domain.product.dto.response.ResGetProduct;
import com.nicky.shoppingmall.domain.product.dto.response.ResGetProductDetail;
import com.nicky.shoppingmall.domain.product.dto.response.ResCreateProduct;
import com.nicky.shoppingmall.domain.product.mapper.ProductMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductMapper productMapper;
    
    @Override
    public Response getProductList(HttpServletRequest request) throws Exception {
        
        String host = request.getScheme() +"://" + request.getServerName() + ":" + request.getServerPort();
        String imagePath = "/api/v1/image/";

        List<ResGetProduct> list = productMapper.getProductList(new GetProductDto(host + imagePath));
        return new Response(list);
    }

    @Override
    public Response getProduct(HttpServletRequest request, Integer id) throws Exception {
        if(id == null || id <= 0) throw new BusinessException(ErrorInfo.INVALID_PRODUCT_ID);

        String host = request.getScheme() +"://" + request.getServerName() + ":" + request.getServerPort();
        String imagePath = "/api/v1/image/";

        GetProductDto dto = new GetProductDto(host + imagePath, id);
        ProductDto product = productMapper.getProduct(dto);

        if(product == null) throw new BusinessException(ErrorInfo.PRODUCT_NOT_FOUND);

        ResGetProductDetail res = new ResGetProductDetail(product);

        res.setMetadataList(productMapper.getProductMetadataList(id));
        res.setSubImageList(productMapper.getProductImageList(dto));
        res.setOptionList(productMapper.getProductOptionList(id));
        
        return new Response(res);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response createProduct(ReqCreateProduct request) throws Exception {
        // 1. 상품 기본정보 등록
        CreateProductDto productDto = CreateProductDto.builder()
            .categoryId(request.getCategoryId())
            .discountId(request.getDiscountId())
            .name(request.getName())
            .description(request.getDescription())
            .supplyPrice(request.getSupplyPrice())
            .consumerPrice(request.getConsumerPrice())
            .productPrice(request.getProductPrice())
            .tax(request.getTax())
            .margin(request.getMargin())
            .tag(CommonUtil.makeTag(request.getName(), request.getDescription()))
            .stock(request.getStock()).build();
        productMapper.createProduct(productDto);

        // 2. 상품 메타데이터 등록 (product_id)
        List<CreateMetadataDto> metadataList = new ArrayList<>();
        for(ReqCreateProduct.Metadata data : request.getMetadataList()) {
            metadataList.add(new CreateMetadataDto(productDto.getId(), data.getFieldName(), data.getFieldValue()));
        }
        if(metadataList.size() <= 0) throw new BusinessException(ErrorInfo.INVALID_PRODUCT_META);
        
        productMapper.createMetadataList(metadataList);
        
        // 3. 상품 옵션 등록 (product_id)
        List<CreateOptionDto> optionList = new ArrayList<>();
        for(ReqCreateProduct.Option data : request.getOptionList()) {
            optionList.add(new CreateOptionDto(productDto.getId(), data.getOptName(), data.getOptValue()));
        }
        if(optionList.size() <= 0) throw new BusinessException(ErrorInfo.INVALID_PRODUCT_OPT);
        
        productMapper.createOptionList(optionList);
        
        return new Response(new ResCreateProduct(productDto.getId()));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response createProductComplete(ReqCreateProductComplete request) throws Exception {
        Integer productId = request.getProductId();
        // 유효 체크
        Boolean isEnableProc = productMapper.isEnableProcess(productId);
        if(!isEnableProc) throw new BusinessException(ErrorInfo.INVALID_PRODUCT_COMPLETE);

        // 1. 상품 메인이미지, 썸네일 이미지 설정
        productMapper.modifyProductImage(new ModifyProductImageDto(productId, request.getMainImageId(), request.getThumbImageId()));

        // 2. 상품 서브이미지 등록
        List<AddProductSubImageDto> subImageList = new ArrayList<>();
        List<ReqCreateProductComplete.SubImage> list = request.getSubImageList();
        for(int i = 0; i < list.size(); i++) {
            ReqCreateProductComplete.SubImage data = list.get(i);
            subImageList.add(new AddProductSubImageDto(productId, data.getImageId(), data.isMain(), i));
        } 
        if(subImageList.size() <= 0) throw new BusinessException(ErrorInfo.INVALID_PRODUCT_SUB_IMAGE);

        productMapper.removeProductSubImageList(productId);
        productMapper.addProductSubImageList(subImageList);

        // 3. 상품 활성화 ~(stock > 0 일 경우 is_show = true)
        productMapper.enableProduct(productId);

        return new Response();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response removeProductList(ReqRemoveProductList request) throws Exception {
        List<Integer> productIdList = request.getProductIdList();
        if(productIdList.size() <= 0) throw new BusinessException(ErrorInfo.INVALID_LIST_ZERO);
        
        productMapper.removeProductList(productIdList);

        return new Response();
    }



    
}

package com.nicky.shoppingmall.domain.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.business.BusinessException;
import com.nicky.shoppingmall.config.error.ErrorInfo;
import com.nicky.shoppingmall.config.util.CommonUtil;
import com.nicky.shoppingmall.domain.product.dto.CreateProductCompleteDto;
import com.nicky.shoppingmall.domain.product.dto.CreateProductDto;
import com.nicky.shoppingmall.domain.product.dto.ProductDto;
import com.nicky.shoppingmall.domain.product.dto.ProductImageDto;
import com.nicky.shoppingmall.domain.product.dto.ProductListDto;
import com.nicky.shoppingmall.domain.product.dto.ProductMetadataDto;
import com.nicky.shoppingmall.domain.product.dto.ProductOptionDto;
import com.nicky.shoppingmall.domain.product.dto.RemoveProductDto;
import com.nicky.shoppingmall.domain.product.mapper.ProductMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductMapper productMapper;
    private final String HOST_IMAGE_PATH = "{scheme}://{serverName}:{serverPort}/api/v1/image/";

    private String getHostImagePath(HttpServletRequest request) {
        return HOST_IMAGE_PATH
                .replace("{scheme}", request.getScheme())
                .replace("{serverName}", request.getServerName())
                .replace("{serverPort}", String.valueOf(request.getServerPort()));
    }

    @Override
    public Response getProductList(HttpServletRequest request) throws Exception {
        
        String hostImagePath = getHostImagePath(request);
        List<ProductListDto.Data> list = productMapper.getProductList(new ProductListDto.Get(hostImagePath)); 

        return new Response(new ProductListDto.Response(list));
    }

    @Override
    public Response getProduct(HttpServletRequest request, Integer id) throws Exception {
        if(id == null || id <= 0) throw new BusinessException(ErrorInfo.INVALID_PRODUCT_ID);

        String hostImagePath = getHostImagePath(request);
        ProductDto.Get dto = new ProductDto.Get(hostImagePath, id);
        ProductDto.Data product = productMapper.getProduct(dto);

        if(product == null) throw new BusinessException(ErrorInfo.PRODUCT_NOT_FOUND);

        ProductDto.Response res = new ProductDto.Response(product);

        res.setMetadataList(productMapper.getProductMetadataList(id));
        res.setSubImageList(productMapper.getProductImageList(dto));
        res.setOptionList(productMapper.getProductOptionList(id));
        
        return new Response(res);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response createProduct(CreateProductDto.Request request) throws Exception {
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
        List<ProductMetadataDto.Insert> metadataList = new ArrayList<>();
        for(CreateProductDto.Request.Metadata data : request.getMetadataList()) {
            metadataList.add(new ProductMetadataDto.Insert(productDto.getId(), data.getFieldName(), data.getFieldValue()));
        }
        if(metadataList.size() <= 0) throw new BusinessException(ErrorInfo.INVALID_PRODUCT_META);
        
        productMapper.createMetadataList(metadataList);
        
        // 3. 상품 옵션 등록 (product_id)
        List<ProductOptionDto.Insert> optionList = new ArrayList<>();
        for(CreateProductDto.Request.Option data : request.getOptionList()) {
            optionList.add(new ProductOptionDto.Insert(productDto.getId(), data.getOptName(), data.getOptValue()));
        }
        if(optionList.size() <= 0) throw new BusinessException(ErrorInfo.INVALID_PRODUCT_OPT);
        
        productMapper.createOptionList(optionList);
        
        return new Response(new CreateProductDto.Response(productDto.getId()));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response createProductComplete(CreateProductCompleteDto.Request request) throws Exception {
        Integer productId = request.getProductId();
        // 유효 체크
        Boolean isEnableProc = productMapper.isEnableProcess(productId);
        if(!isEnableProc) throw new BusinessException(ErrorInfo.INVALID_PRODUCT_COMPLETE);

        // 1. 상품 메인이미지, 썸네일 이미지 설정
        productMapper.modifyProductImage(ProductDto.Update.builder()
                                                            .productId(productId)
                                                            .mainImageId(request.getMainImageId())
                                                            .thumbImageId(request.getThumbImageId()).build());

        // 2. 상품 서브이미지 등록
        List<ProductImageDto.Insert> subImageList = new ArrayList<>();
        List<CreateProductCompleteDto.SubImage> list = request.getSubImageList();
        for(int i = 0; i < list.size(); i++) {
            CreateProductCompleteDto.SubImage data = list.get(i);
            subImageList.add(new ProductImageDto.Insert(productId, data.getImageId(), data.isMain(), i));
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
    public Response removeProductList(RemoveProductDto.Request request) throws Exception {
        List<Integer> productIdList = request.getProductIdList();
        if(productIdList.size() <= 0) throw new BusinessException(ErrorInfo.INVALID_LIST_ZERO);
        
        productMapper.removeProductList(productIdList);

        return new Response();
    }



    
}

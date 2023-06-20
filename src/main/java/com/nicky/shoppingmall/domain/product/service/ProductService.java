package com.nicky.shoppingmall.domain.product.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.product.dto.CreateProductCompleteDto;
import com.nicky.shoppingmall.domain.product.dto.CreateProductDto;
import com.nicky.shoppingmall.domain.product.dto.RemoveProductDto;

import jakarta.servlet.http.HttpServletRequest;

public interface ProductService {
    public Response getProductList(HttpServletRequest request) throws Exception;
    public Response getProduct(HttpServletRequest request, Integer id) throws Exception;
    public Response createProduct(CreateProductDto.Request request) throws Exception;
    public Response createProductComplete(CreateProductCompleteDto.Request request) throws Exception;
    public Response removeProductList(RemoveProductDto.Request request) throws Exception;
}

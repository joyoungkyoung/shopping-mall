package com.nicky.shoppingmall.domain.product.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.product.dto.request.ReqCreateProduct;
import com.nicky.shoppingmall.domain.product.dto.request.ReqCreateProductComplete;
import com.nicky.shoppingmall.domain.product.dto.request.ReqRemoveProductList;

import jakarta.servlet.http.HttpServletRequest;

public interface ProductService {
    public Response getProductList(HttpServletRequest request) throws Exception;
    public Response getProduct(HttpServletRequest request, Integer id) throws Exception;
    public Response createProduct(ReqCreateProduct request) throws Exception;
    public Response createProductComplete(ReqCreateProductComplete request) throws Exception;
    public Response removeProductList(ReqRemoveProductList request) throws Exception;
}

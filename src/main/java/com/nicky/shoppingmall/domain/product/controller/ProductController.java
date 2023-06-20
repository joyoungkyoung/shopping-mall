package com.nicky.shoppingmall.domain.product.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nicky.shoppingmall.config.Constant;
import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.product.dto.request.ReqCreateProduct;
import com.nicky.shoppingmall.domain.product.dto.request.ReqCreateProductComplete;
import com.nicky.shoppingmall.domain.product.dto.request.ReqRemoveProductList;
import com.nicky.shoppingmall.domain.product.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(Constant.API_VER1 + "/product")
    public Response getProductList(HttpServletRequest request) throws Exception {
        return productService.getProductList(request);
    }

    @GetMapping(Constant.API_VER1 + "/product/{id}")
    public Response getProduct(HttpServletRequest request, @PathVariable Integer id) throws Exception {
        return productService.getProduct(request, id);
    }

    @PreAuthorize(Constant.HAS_ENABLE_UPDATE)
    @PostMapping(Constant.API_VER1 + "/product")
    public Response createProduct(@RequestBody ReqCreateProduct request) throws Exception {
        return productService.createProduct(request);
    }

    @PreAuthorize(Constant.HAS_ENABLE_UPDATE)
    @PostMapping(Constant.API_VER1 + "/product/complete")
    public Response createProductComplete(@RequestBody ReqCreateProductComplete request) throws Exception {
        return productService.createProductComplete(request);
    }

    @PreAuthorize(Constant.HAS_ENABLE_UPDATE)
    @DeleteMapping(Constant.API_VER1 + "/product")
    public Response removeProductList(@RequestBody ReqRemoveProductList request) throws Exception {
        return productService.removeProductList(request);
    }
}

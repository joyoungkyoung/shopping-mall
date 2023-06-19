package com.nicky.shoppingmall.domain.image.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nicky.shoppingmall.config.Constant;
import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.image.service.ImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    public static final String PATH_PRODUCT = "/image/product/{id}/";
    public static final String PATH_PRODUCT_SUB_IMAGE = PATH_PRODUCT + "/sub-image/";


    @PostMapping(Constant.API_VER1 + "/image/product")
    public Response uploadProductImage(@RequestPart Integer productId, @RequestPart(required = false) List<MultipartFile> fileList) throws Exception {
        String folderPath = PATH_PRODUCT.replace("{id}", String.valueOf(productId));

        return imageService.uploadImage(productId, folderPath, fileList);
    }

    @PostMapping(Constant.API_VER1 + "/image/product/sub")
    public Response uploadProductSubImage(@RequestPart Integer productId, @RequestPart(required = false) List<MultipartFile> fileList) throws Exception {
        String folderPath = PATH_PRODUCT_SUB_IMAGE.replace("{id}", String.valueOf(productId));

        return imageService.uploadImage(productId, folderPath, fileList);
    }

    @GetMapping(Constant.API_VER1 + "/image/{id}")
    public ResponseEntity<UrlResource> getImage(@PathVariable Integer id) throws IOException {
        return imageService.getImage(id);
    }
}

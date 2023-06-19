package com.nicky.shoppingmall.domain.image.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.nicky.shoppingmall.config.Response;

public interface ImageService {
    public Response uploadImage(Integer productId, String folderPath, List<MultipartFile> fileList) throws IllegalStateException, IOException, Exception;
    public ResponseEntity<UrlResource> getImage(Integer id) throws MalformedURLException, IOException;
}

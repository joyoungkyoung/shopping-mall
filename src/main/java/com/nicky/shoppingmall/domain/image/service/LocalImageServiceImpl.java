package com.nicky.shoppingmall.domain.image.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.business.BusinessException;
import com.nicky.shoppingmall.config.error.ErrorInfo;
import com.nicky.shoppingmall.domain.image.dto.CreateImageDto;
import com.nicky.shoppingmall.domain.image.dto.ImageDto;
import com.nicky.shoppingmall.domain.image.mapper.ImageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalImageServiceImpl implements ImageService {
    private final ImageMapper imageMapper;

    @Value("${file-path}")
    private String savePath;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response uploadImage(Integer productId, String folderPath, List<MultipartFile> fileList) throws Exception {
        // 이미지 파일 체크
        boolean hasNotImage = fileList.stream().anyMatch(f -> {
            String ext = StringUtils.getFilenameExtension(f.getOriginalFilename());
            return !ext.equalsIgnoreCase("png") && !ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("jpeg");
        });
        if(hasNotImage) throw new BusinessException(ErrorInfo.INVALID_IMAGE_FORMAT);
        
        List<CreateImageDto> imageList = new ArrayList<>();
        for(MultipartFile file : fileList) {
            if(!file.isEmpty()) {
                String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
                String filename = StringUtils.getFilename(StringUtils.stripFilenameExtension(file.getOriginalFilename()));
                String path = folderPath + filename + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))+"."+ext;
                File dest = new File(savePath + path);
                dest.mkdirs();
                file.transferTo(dest);
                imageList.add(new CreateImageDto(path, file.getOriginalFilename()));
            }
        }

        if(imageList.size() <= 0) throw new BusinessException(ErrorInfo.IMAGE_NOT_EXIST);

        imageMapper.createImageList(imageList);
        
        return new Response(imageList);
    }

    @Override
    public ResponseEntity<UrlResource> getImage(Integer id) throws IOException {
        ImageDto image = imageMapper.getImage(id);

        MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(Paths.get(image.getUrl())));
        UrlResource resource = new UrlResource("file:" + savePath + image.getUrl());
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, mediaType.toString())
            .body(resource);
    }
    
}

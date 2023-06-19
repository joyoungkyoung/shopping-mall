package com.nicky.shoppingmall.domain.image.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nicky.shoppingmall.domain.image.dto.CreateImageDto;
import com.nicky.shoppingmall.domain.image.dto.ImageDto;

/**
 * prefix : get, create, remove, modify, change
 */
@Mapper
public interface ImageMapper {
    public void createImageList(List<CreateImageDto> dtoList);
    public ImageDto getImage(Integer id);
}

package com.nicky.shoppingmall.domain.category.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nicky.shoppingmall.domain.category.dto.AddCategoryDto;
import com.nicky.shoppingmall.domain.category.dto.ChangeCategoryDto;
import com.nicky.shoppingmall.domain.category.dto.GetCategoryDto;

@Mapper
public interface CategoryMapper {
    public List<GetCategoryDto> getCategoryList();
    public Integer getLatestOrderByParent(Integer parentCategoryId);
    public void addCategoryList(List<AddCategoryDto> dtoList);
    public void changeCategoryList(List<ChangeCategoryDto> dtoList);
    public void removeCategoryList(List<Integer> categoryIdList);
    public void reOrder();
}

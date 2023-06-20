package com.nicky.shoppingmall.domain.category.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.category.dto.AddCategoryDto;
import com.nicky.shoppingmall.domain.category.dto.ChangeCategoryDto;
import com.nicky.shoppingmall.domain.category.dto.RemoveCategory;

public interface CategoryService {
    public Response getCategoryList() throws Exception;
    public Response addCategory(AddCategoryDto.Request request) throws Exception;
    public Response changeCategory(ChangeCategoryDto.Request request) throws Exception;
    public Response removeCategory(RemoveCategory.Request request) throws Exception;
}

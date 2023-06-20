package com.nicky.shoppingmall.domain.category.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nicky.shoppingmall.config.Constant;
import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.category.dto.AddCategoryDto;
import com.nicky.shoppingmall.domain.category.dto.ChangeCategoryDto;
import com.nicky.shoppingmall.domain.category.dto.RemoveCategory;
import com.nicky.shoppingmall.domain.category.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(Constant.API_VER1 + "/category")
    public Response getCategoryList() throws Exception{
        return categoryService.getCategoryList();
    }

    @PostMapping(Constant.API_VER1 + "/category")
    public Response addCategory(@RequestBody @Valid AddCategoryDto.Request request) throws Exception{
        return categoryService.addCategory(request);
    }

    @PatchMapping(Constant.API_VER1 + "/category")
    public Response changeCategory(@RequestBody @Valid ChangeCategoryDto.Request request) throws Exception{
        return categoryService.changeCategory(request);
    }

    @DeleteMapping(Constant.API_VER1 + "/category")
    public Response removeCategory(@RequestBody RemoveCategory.Request request) throws Exception{
        return categoryService.removeCategory(request);
    }


}

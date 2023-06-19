package com.nicky.shoppingmall.domain.category.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nicky.shoppingmall.config.Constant;
import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.category.dto.ReqAddCategory;
import com.nicky.shoppingmall.domain.category.dto.ReqChangeCategory;
import com.nicky.shoppingmall.domain.category.dto.ReqRemoveCategory;
import com.nicky.shoppingmall.domain.category.service.CategoryService;

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
    public Response addCategory(@RequestBody ReqAddCategory request) throws Exception{
        return categoryService.addCategory(request);
    }

    @PatchMapping(Constant.API_VER1 + "/category")
    public Response changeCategory(@RequestBody ReqChangeCategory request) throws Exception{
        return categoryService.changeCategory(request);
    }

    @DeleteMapping(Constant.API_VER1 + "/category")
    public Response removeCategory(@RequestBody ReqRemoveCategory request) throws Exception{
        return categoryService.removeCategory(request);
    }


}

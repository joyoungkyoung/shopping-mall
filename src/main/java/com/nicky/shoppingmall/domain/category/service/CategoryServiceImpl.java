package com.nicky.shoppingmall.domain.category.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.category.dto.AddCategoryDto;
import com.nicky.shoppingmall.domain.category.dto.ChangeCategoryDto;
import com.nicky.shoppingmall.domain.category.dto.CategoryDto;
import com.nicky.shoppingmall.domain.category.dto.RemoveCategory;
import com.nicky.shoppingmall.domain.category.mapper.CategoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    @Override
    public Response getCategoryList() throws Exception {
        List<CategoryDto.Data> list = categoryMapper.getCategoryList();
        CategoryDto.Response res = new CategoryDto.Response(list);

        return new Response(res);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response addCategory(AddCategoryDto.Request request) throws Exception {
        List<AddCategoryDto> categoryList = new ArrayList<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        
        request.getCategoryList().stream().forEach(c -> {
            Integer parentId = c.getParentCategoryId();

            // 1. 카테고리 parent별 최신 순서 값 반환
            Integer cateOrder = parentMap.get(parentId);
            if(cateOrder == null) {
                cateOrder = categoryMapper.getLatestOrderByParent(parentId);
                parentMap.put(parentId, cateOrder);
            }
            // 2. 카테고리 데이터 추가 (순서 값 +1)
            Integer order = cateOrder.intValue() + 1;
            categoryList.add(new AddCategoryDto(parentId, c.getName(), order));
            parentMap.put(parentId, order);
        });
        
        if(categoryList.size() <= 0) throw new Exception("추가할 카테고리가 없습니다.");

        categoryMapper.addCategoryList(categoryList);

        return new Response();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response changeCategory(ChangeCategoryDto.Request request) throws Exception {
        // 1. 카테고리 데이터 수정
        List<ChangeCategoryDto> categoryList = new ArrayList<>();
        
        request.getCategoryList().stream().forEach(c -> {
            categoryList.add(new ChangeCategoryDto(c.getCategoryId(), c.getName()));
        });
        
        if(categoryList.size() <= 0) throw new Exception("수정할 카테고리가 없습니다.");

        categoryMapper.changeCategoryList(categoryList);

        return new Response();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response removeCategory(RemoveCategory.Request request) throws Exception {
        // 1. 카테고리 데이터 삭제
        categoryMapper.removeCategoryList(request.getCategoryIdList());

        // 2. parent별 카테고리 order 재정리
        categoryMapper.reOrder();
        
        return new Response();    
    }
    
}

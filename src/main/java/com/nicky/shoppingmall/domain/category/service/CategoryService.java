package com.nicky.shoppingmall.domain.category.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.category.dto.ReqAddCategory;
import com.nicky.shoppingmall.domain.category.dto.ReqChangeCategory;
import com.nicky.shoppingmall.domain.category.dto.ReqRemoveCategory;

public interface CategoryService {
    public Response getCategoryList() throws Exception;
    public Response addCategory(ReqAddCategory request) throws Exception;
    public Response changeCategory(ReqChangeCategory request) throws Exception;
    public Response removeCategory(ReqRemoveCategory request) throws Exception;
}

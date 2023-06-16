package com.nicky.shoppingmall.domain.adminMng.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.userDetails.MyUserDetails;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqCreateAdminUser;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqDuplUsername;

public interface AdminUserService {
    public Response createAdminUser(ReqCreateAdminUser request, MyUserDetails myUserDetails) throws Exception;
    public Response duplUsername(ReqDuplUsername request) throws Exception;
}

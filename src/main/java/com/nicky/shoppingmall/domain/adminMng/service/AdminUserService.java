package com.nicky.shoppingmall.domain.adminMng.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.userDetails.MyUserDetails;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqCreateAdminUser;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqDuplUsername;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqModifyAdminUser;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqRemoveAdminUser;

public interface AdminUserService {
    public Response getAdminUserList() throws Exception;
    public Response createAdminUser(ReqCreateAdminUser request, MyUserDetails myUserDetails) throws Exception;
    public Response duplUsername(ReqDuplUsername request) throws Exception;
    public Response modifyAdminUser(ReqModifyAdminUser request, MyUserDetails myUserDetails) throws Exception;
    public Response removeAdminUser(ReqRemoveAdminUser request) throws Exception;
}

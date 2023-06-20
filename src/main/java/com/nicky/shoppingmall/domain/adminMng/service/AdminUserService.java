package com.nicky.shoppingmall.domain.adminMng.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.userDetails.MyUserDetails;
import com.nicky.shoppingmall.domain.adminMng.dto.CreateAdminDto;
import com.nicky.shoppingmall.domain.adminMng.dto.DuplUsernameDto;
import com.nicky.shoppingmall.domain.adminMng.dto.ModifyAdminDto;
import com.nicky.shoppingmall.domain.adminMng.dto.RemoveAdminDto;

public interface AdminUserService {
    public Response getAdminUserList() throws Exception;
    public Response createAdminUser(CreateAdminDto.Request request, MyUserDetails myUserDetails) throws Exception;
    public Response duplUsername(DuplUsernameDto.Request request) throws Exception;
    public Response modifyAdminUser(ModifyAdminDto.Request request, MyUserDetails myUserDetails) throws Exception;
    public Response removeAdminUser(RemoveAdminDto.Request request) throws Exception;
}

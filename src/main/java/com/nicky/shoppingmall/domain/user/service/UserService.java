package com.nicky.shoppingmall.domain.user.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.user.dto.ReqCreateAccount;
import com.nicky.shoppingmall.domain.user.dto.ReqLogin;

public interface UserService {
    public Response create(ReqCreateAccount request) throws Exception;
    public Response login(ReqLogin request) throws Exception;
}

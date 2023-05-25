package com.nicky.shoppingmall.domain.auth.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.auth.dto.ReqCreateAccount;
import com.nicky.shoppingmall.domain.auth.dto.ReqLogin;

public interface AuthService {
    public Response create(ReqCreateAccount request) throws Exception;
    public Response login(ReqLogin request) throws Exception;
}

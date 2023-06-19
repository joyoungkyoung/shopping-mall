package com.nicky.shoppingmall.domain.auth.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.auth.dto.ReqAdminLogin;

import jakarta.servlet.http.HttpServletRequest;

public interface AdminAuthService {
    public Response login(ReqAdminLogin request) throws Exception;    
    public Response refresh(HttpServletRequest request, String refreshToken) throws Exception;
}

package com.nicky.shoppingmall.domain.auth.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.auth.dto.ReqAdminLogin;

public interface AdminAuthService {
    public Response login(ReqAdminLogin request) throws Exception;    
}

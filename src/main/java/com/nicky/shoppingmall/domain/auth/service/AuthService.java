package com.nicky.shoppingmall.domain.auth.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.auth.dto.CreateUserDto;
import com.nicky.shoppingmall.domain.auth.dto.LoginDto;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    public Response create(CreateUserDto.Request request) throws Exception;
    public Response login(LoginDto.Request request) throws Exception;
    public Response refresh(HttpServletRequest request, String refreshToken) throws Exception;
}

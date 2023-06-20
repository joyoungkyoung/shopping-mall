package com.nicky.shoppingmall.domain.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nicky.shoppingmall.config.Constant;
import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.auth.dto.AdminLoginDto;
import com.nicky.shoppingmall.domain.auth.service.AdminAuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminAuthController {
    private final AdminAuthService adminAuthService;

    @PostMapping(Constant.API_VER1 + "/admin/auth/login")
    public Response login(@RequestBody @Valid AdminLoginDto.Request request) throws Exception {
        return adminAuthService.login(request);
    }

    @PostMapping(Constant.API_VER1 + "/admin/auth/refresh")
    public Response refresh(HttpServletRequest request, @RequestHeader(value = "REFRESH-TOKEN") String refreshToken) throws Exception {
        return adminAuthService.refresh(request, refreshToken);
    }
}

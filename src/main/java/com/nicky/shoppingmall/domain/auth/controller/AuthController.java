package com.nicky.shoppingmall.domain.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicky.shoppingmall.config.Constant;
import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.auth.dto.ReqCreateAccount;
import com.nicky.shoppingmall.domain.auth.dto.ReqLogin;
import com.nicky.shoppingmall.domain.auth.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping(Constant.API_VER1 + "/signup")
    public Response signup(@RequestBody @Valid ReqCreateAccount request) throws Exception {
        return authService.create(request);
    }

    @PostMapping(Constant.API_VER1 + "/login")
    public Response login(@RequestBody @Valid ReqLogin request) throws Exception {
        return authService.login(request);
    }

    @PostMapping(Constant.API_VER1 + "/refresh")
    public Response refresh(HttpServletRequest request, @RequestHeader(value = "REFRESH-TOKEN") String refreshToken) throws Exception {
        return authService.refresh(request, refreshToken);
    }
}

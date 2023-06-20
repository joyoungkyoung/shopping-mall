package com.nicky.shoppingmall.domain.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nicky.shoppingmall.config.Constant;
import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.auth.dto.CreateUserDto;
import com.nicky.shoppingmall.domain.auth.dto.LoginDto;
import com.nicky.shoppingmall.domain.auth.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(Constant.API_VER1 + "/auth/signup")
    public Response signup(@RequestBody @Valid CreateUserDto.Request request) throws Exception {
        return authService.create(request);
    }

    @PostMapping(Constant.API_VER1 + "/auth/login")
    public Response login(@RequestBody @Valid LoginDto.Request request) throws Exception {
        return authService.login(request);
    }

    @PostMapping(Constant.API_VER1 + "/auth/refresh")
    public Response refresh(HttpServletRequest request, @RequestHeader(value = "REFRESH-TOKEN") String refreshToken) throws Exception {
        return authService.refresh(request, refreshToken);
    }
}

package com.nicky.shoppingmall.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.user.dto.ReqCreateAccount;
import com.nicky.shoppingmall.domain.user.dto.ReqLogin;
import com.nicky.shoppingmall.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/signup")
    public Response signup(@RequestBody @Valid ReqCreateAccount request) throws Exception {
        return userService.create(request);
    }

    @PostMapping("/api/login")
    public Response login(@RequestBody @Valid ReqLogin request) throws Exception {
        return userService.login(request);
    }
}
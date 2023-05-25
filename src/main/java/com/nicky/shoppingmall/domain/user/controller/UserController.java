package com.nicky.shoppingmall.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.domain.auth.dto.ReqCreateAccount;
import com.nicky.shoppingmall.domain.auth.dto.ReqLogin;
import com.nicky.shoppingmall.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    
}

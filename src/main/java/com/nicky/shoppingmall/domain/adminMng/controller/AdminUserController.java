package com.nicky.shoppingmall.domain.adminMng.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nicky.shoppingmall.config.Constant;
import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.userDetails.MyUserDetails;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqCreateAdminUser;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqDuplUsername;
import com.nicky.shoppingmall.domain.adminMng.service.AdminUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;

    // 관리자 리스트 조히
    // 관리자 등록
    @PostMapping(Constant.API_VER1 + "/admin/mng")
    public Response createAdminUser(@RequestBody ReqCreateAdminUser request, @AuthenticationPrincipal MyUserDetails myUserDetails) throws Exception {
        return adminUserService.createAdminUser(request, myUserDetails);
    }

    @PostMapping(Constant.API_VER1 + "/admin/mng/dupl-username")
    public Response duplUsername(@RequestBody ReqDuplUsername request) throws Exception {
        return adminUserService.duplUsername(request);
    }
    // 관리자 수정
    // 관리자 삭제
}

package com.nicky.shoppingmall.domain.adminMng.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nicky.shoppingmall.config.Constant;
import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.userDetails.MyUserDetails;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqCreateAdminUser;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqDuplUsername;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqModifyAdminUser;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqRemoveAdminUser;
import com.nicky.shoppingmall.domain.adminMng.service.AdminUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;

    // 관리자 리스트 조회
    @GetMapping(Constant.API_VER1 + "/admin/mng")
    public Response getAdminUserList() throws Exception {
        return adminUserService.getAdminUserList();
    }

    // 관리자 등록
    @PostMapping(Constant.API_VER1 + "/admin/mng")
    public Response createAdminUser(@RequestBody @Valid ReqCreateAdminUser request, @AuthenticationPrincipal MyUserDetails myUserDetails) throws Exception {
        return adminUserService.createAdminUser(request, myUserDetails);
    }

    @PostMapping(Constant.API_VER1 + "/admin/mng/dupl-username")
    public Response duplUsername(@RequestBody @Valid ReqDuplUsername request) throws Exception {
        return adminUserService.duplUsername(request);
    }

    // 관리자 수정 
    @PatchMapping(Constant.API_VER1 + "/admin/mng")
    public Response modifyAdminUser(@RequestBody @Valid ReqModifyAdminUser request, @AuthenticationPrincipal MyUserDetails myUserDetails) throws Exception {
        return adminUserService.modifyAdminUser(request, myUserDetails);
    }

    // 관리자 삭제
    @DeleteMapping(Constant.API_VER1 + "/admin/mng")
    public Response removeAdminUser(@RequestBody @Valid ReqRemoveAdminUser request) throws Exception {
        return adminUserService.removeAdminUser(request);
    }
}

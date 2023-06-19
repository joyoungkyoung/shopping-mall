package com.nicky.shoppingmall.domain.adminMng.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.business.BusinessException;
import com.nicky.shoppingmall.config.error.ErrorInfo;
import com.nicky.shoppingmall.config.userDetails.MyUserDetails;
import com.nicky.shoppingmall.domain.adminMng.dto.CreateAdminDto;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqCreateAdminUser;
import com.nicky.shoppingmall.domain.adminMng.dto.ReqDuplUsername;
import com.nicky.shoppingmall.domain.adminMng.mapper.AdminUserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {
    private final AdminUserMapper adminUserMapper;

    private final PasswordEncoder passwordEncoder;
    
    @Override
    public Response createAdminUser(ReqCreateAdminUser request, MyUserDetails myUserDetails) throws Exception {
        // 아이디 중복체크
        Boolean isExist = adminUserMapper.isExistUsername(request.getUsername());
        if(isExist) throw new BusinessException(ErrorInfo.DUPLICATE_USERNAME);

        // 권한체크 : request에 저장된 코드가 반환된 데이터에 없을 때 권한 에러 (권한 상승 필요)
        String authority = myUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        authority = authority.replace("ROLE_", "");

        String creatableCode = adminUserMapper.getCreatableCode(authority);
        String[] codeArray = creatableCode.split("\\|");
        List<String> codeList = new ArrayList<>();
        for(String code : codeArray) codeList.add(code);
        
        if(!codeList.contains(request.getAuthorityCode())) throw new BusinessException(ErrorInfo.REQUIRE_ELEVATION_OF_PRIVILEGE);

        adminUserMapper.createAdmin(CreateAdminDto.builder()
                                                .username(request.getUsername())
                                                .password(passwordEncoder.encode(request.getPassword()))
                                                .nickname(request.getNickname())
                                                .authorityCode(request.getAuthorityCode()).build());
        

        // 어드민 계정 생성
        return new Response();
    }

    @Override
    public Response duplUsername(ReqDuplUsername request) throws Exception {
        return new Response(adminUserMapper.isExistUsername(request.getUsername()));
    }
    
}

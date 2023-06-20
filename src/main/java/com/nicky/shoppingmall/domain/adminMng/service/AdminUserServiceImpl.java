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
import com.nicky.shoppingmall.domain.adminMng.dto.AdminDto;
import com.nicky.shoppingmall.domain.adminMng.dto.CreateAdminDto;
import com.nicky.shoppingmall.domain.adminMng.dto.DuplUsernameDto;
import com.nicky.shoppingmall.domain.adminMng.dto.ModifyAdminDto;
import com.nicky.shoppingmall.domain.adminMng.dto.RemoveAdminDto;
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
    public Response getAdminUserList() throws Exception {
        AdminDto.Response res = new AdminDto.Response(adminUserMapper.getAdminList());
        
        return new Response(res);
    }

    private boolean hasAuthority(MyUserDetails myUserDetails, String authorityCode) {
        String authority = myUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        authority = authority.replace("ROLE_", "");

        String creatableCode = adminUserMapper.getCreatableCode(authority);
        String[] codeArray = creatableCode.split("\\|");
        List<String> codeList = new ArrayList<>();
        for(String code : codeArray) codeList.add(code);

        return codeList.contains(authorityCode);
    }

    @Override
    public Response createAdminUser(CreateAdminDto.Request request, MyUserDetails myUserDetails) throws Exception {
        // 아이디 중복체크
        Boolean isExist = adminUserMapper.isExistUsername(request.getUsername());
        if(isExist) throw new BusinessException(ErrorInfo.DUPLICATE_USERNAME);

        // 권한체크 : request에 저장된 코드가 반환된 데이터에 없을 때 권한 에러 (권한 상승 필요)
        boolean canProcess = hasAuthority(myUserDetails, request.getAuthorityCode());
        if(!canProcess) throw new BusinessException(ErrorInfo.REQUIRE_ELEVATION_OF_PRIVILEGE);

        adminUserMapper.createAdmin(CreateAdminDto.builder()
                                                .username(request.getUsername())
                                                .password(passwordEncoder.encode(request.getPassword()))
                                                .nickname(request.getNickname())
                                                .authorityCode(request.getAuthorityCode()).build());
        

        // 어드민 계정 생성
        return new Response();
    }

    @Override
    public Response duplUsername(DuplUsernameDto.Request request) throws Exception {
        DuplUsernameDto.Response res = new DuplUsernameDto.Response(adminUserMapper.isExistUsername(request.getUsername()));
        
        return new Response(res);
    }

    @Override
    public Response modifyAdminUser(ModifyAdminDto.Request request, MyUserDetails myUserDetails) throws Exception {
        // 권한체크 : request에 저장된 코드가 반환된 데이터에 없을 때 권한 에러 (권한 상승 필요)
        boolean canProcess = hasAuthority(myUserDetails, request.getAuthorityCode());
        if(!canProcess) throw new BusinessException(ErrorInfo.REQUIRE_ELEVATION_OF_PRIVILEGE);

        adminUserMapper.modifyAdmin(new ModifyAdminDto(request.getId(), request.getNickname(), request.getAuthorityCode()));
    
        return new Response();
    }

    @Override
    public Response removeAdminUser(RemoveAdminDto.Request request) throws Exception {
        List<Integer> list = request.getAdminIdList();
        if(list.size() <= 0) throw new BusinessException(ErrorInfo.INVALID_LIST_ZERO);
        
        adminUserMapper.removeAdminList(list);

        return new Response();
    }
    
}

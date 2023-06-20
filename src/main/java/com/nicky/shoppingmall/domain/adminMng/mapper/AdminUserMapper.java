package com.nicky.shoppingmall.domain.adminMng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nicky.shoppingmall.domain.adminMng.dto.AdminDto;
import com.nicky.shoppingmall.domain.adminMng.dto.CreateAdminDto;
import com.nicky.shoppingmall.domain.adminMng.dto.ModifyAdminDto;

@Mapper
public interface AdminUserMapper {
    // 수정가능 권한 조회
    public String getCreatableCode(String code);
    
    // 어드민 조회
    public List<AdminDto.Info> getAdminList();

    // 어드민 등록
    public void createAdmin(CreateAdminDto dto);
    public Boolean isExistUsername(String username);

    // 어드민 수정
    public void modifyAdmin(ModifyAdminDto dto);
    
    // 어드민 삭제
    public void removeAdminList(List<Integer> idList);
}

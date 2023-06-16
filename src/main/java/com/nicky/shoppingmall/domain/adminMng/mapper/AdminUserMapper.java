package com.nicky.shoppingmall.domain.adminMng.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nicky.shoppingmall.domain.adminMng.dto.CreateAdminDto;

@Mapper
public interface AdminUserMapper {
    public String getCreatableCode(String code);
    public void createAdmin(CreateAdminDto dto);
    public Boolean isExistUsername(String username);
}

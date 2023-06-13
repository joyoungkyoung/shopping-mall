package com.nicky.shoppingmall.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nicky.shoppingmall.domain.user.dto.ModifyAddressIdDto;

/**
 * prefix : get, create, remove, modify, change
 */
@Mapper
public interface UserMapper {
    public void modifyAddressId(ModifyAddressIdDto dto);
}

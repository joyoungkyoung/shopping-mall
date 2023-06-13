package com.nicky.shoppingmall.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nicky.shoppingmall.domain.user.dto.CreateNewAddressDto;

/**
 * prefix : get, create, remove, modify, change
 */
@Mapper
public interface AddressMapper {
    public void createNewAddress(CreateNewAddressDto dto);
}

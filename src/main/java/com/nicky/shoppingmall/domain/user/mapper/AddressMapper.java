package com.nicky.shoppingmall.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nicky.shoppingmall.domain.user.dto.AddNewAddressDto;

/**
 * prefix : get, add, register, remove, modify, change
 */
@Mapper
public interface AddressMapper {
    public void addNewAddress(AddNewAddressDto dto);
}

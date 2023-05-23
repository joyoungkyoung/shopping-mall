package com.nicky.shoppingmall.domain.user.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * prefix : get, add, register, remove, modify, change
 */
@Mapper
public interface UserMapper {
    public UserDetails getUserDetails(String username);
    public void register(Map<String, Object> map);
    public void changeRefreshToken(Map<String, Object> map);
}

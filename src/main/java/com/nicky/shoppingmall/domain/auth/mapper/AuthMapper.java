package com.nicky.shoppingmall.domain.auth.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import com.nicky.shoppingmall.domain.auth.dto.RefreshTokenDto;
import com.nicky.shoppingmall.domain.auth.dto.RegisterDto;

@Mapper
public interface AuthMapper {
    public UserDetails getUserDetails(String username);
    public void register(RegisterDto dto);
    public void changeRefreshToken(Map<String, Object> map);
    public RefreshTokenDto getByRefreshToken(String token);
}

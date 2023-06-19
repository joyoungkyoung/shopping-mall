package com.nicky.shoppingmall.domain.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import com.nicky.shoppingmall.domain.auth.dto.ChangeRefreshTokenDto;
import com.nicky.shoppingmall.domain.auth.dto.RefreshTokenDto;

@Mapper
public interface AdminAuthMapper {
    public UserDetails getUserDetails(String username);
    public void changeRefreshToken(ChangeRefreshTokenDto dto);
    public RefreshTokenDto getByRefreshToken(String token);
}

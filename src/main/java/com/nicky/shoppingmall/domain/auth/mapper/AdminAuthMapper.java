package com.nicky.shoppingmall.domain.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import com.nicky.shoppingmall.domain.auth.dto.RefreshTokenDto;

@Mapper
public interface AdminAuthMapper {
    public UserDetails getUserDetails(String username);
    public void changeRefreshToken(RefreshTokenDto.Change dto);
    public RefreshTokenDto getByRefreshToken(String token);
}

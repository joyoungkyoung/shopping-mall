package com.nicky.shoppingmall.domain.adminMng.dto;

import java.time.Instant;

import lombok.Getter;

@Getter
public class AdminUserDto {
    private Integer id;
    private String username;
    private String nickname;
    private String authorityCode;    
    private Instant createDate;
    private Instant updateDate;
}

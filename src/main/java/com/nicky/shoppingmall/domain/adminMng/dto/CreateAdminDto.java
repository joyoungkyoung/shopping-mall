package com.nicky.shoppingmall.domain.adminMng.dto;

import lombok.Builder;

public class CreateAdminDto {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String authorityCode;

    @Builder
    public CreateAdminDto(String username, String password, String nickname, String authorityCode) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.authorityCode = authorityCode;
    }

    public Integer getId() { return id; }
}

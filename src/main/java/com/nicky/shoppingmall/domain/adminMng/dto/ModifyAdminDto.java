package com.nicky.shoppingmall.domain.adminMng.dto;

public class ModifyAdminDto {
    private Integer id;
    private String nickname;
    private String authorityCode;

    public ModifyAdminDto(Integer id, String nickname, String authorityCode) {
        this.id = id;
        this.nickname = nickname;
        this.authorityCode = authorityCode;
    }
}

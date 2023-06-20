package com.nicky.shoppingmall.domain.adminMng.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;


public class AdminDto {
    
    @Getter
    public static class Info {
        private Integer id;
        private String username;
        private String nickname;
        private String authorityCode;    
        private Instant createDate;
        private Instant updateDate;
    }
    
    @Getter
    @AllArgsConstructor
    public static class Response {
        private List<Info> list;
    }
}

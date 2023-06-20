package com.nicky.shoppingmall.domain.adminMng.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;


public class RemoveAdminDto {
    
    @Getter
    public static class Request {
        @NotNull
        private List<Integer> adminIdList;
    }
    
}

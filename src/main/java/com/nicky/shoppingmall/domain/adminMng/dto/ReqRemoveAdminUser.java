package com.nicky.shoppingmall.domain.adminMng.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReqRemoveAdminUser {
    @NotNull
    private List<Integer> adminIdList;
}

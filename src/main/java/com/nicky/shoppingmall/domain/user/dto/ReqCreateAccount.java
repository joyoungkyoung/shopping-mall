package com.nicky.shoppingmall.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReqCreateAccount {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}

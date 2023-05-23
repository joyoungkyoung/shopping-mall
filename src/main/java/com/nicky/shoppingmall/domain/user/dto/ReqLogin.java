package com.nicky.shoppingmall.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReqLogin {
    @NotBlank
    public String username;
    @NotBlank
    public String password;
}

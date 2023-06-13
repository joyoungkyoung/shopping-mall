package com.nicky.shoppingmall.domain.user.dto;

import lombok.Builder;

@Builder 
public class CreateNewAddressDto {
    private int id; // useGeneratedKeys
    private int userId;
    private String zipCode;
    private String addressMain;
    private String addressSub;

    public int getId() {return id;}
}

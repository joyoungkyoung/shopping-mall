package com.nicky.shoppingmall.domain.image.dto;

public class CreateImageDto {
    private Integer id;
    private String url;
    private String name;

    public CreateImageDto(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public Integer getId() { return id; }
}

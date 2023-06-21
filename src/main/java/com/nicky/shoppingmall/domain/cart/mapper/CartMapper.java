package com.nicky.shoppingmall.domain.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nicky.shoppingmall.domain.cart.dto.AddCartDto;
import com.nicky.shoppingmall.domain.cart.dto.GetCartDto;
import com.nicky.shoppingmall.domain.cart.dto.ModifyCartDto;
import com.nicky.shoppingmall.domain.cart.dto.RemoveCartDto;

@Mapper
public interface CartMapper {
    public void addCart(AddCartDto dto);
    public void removeCart(RemoveCartDto dto);
    public void modifyCart(ModifyCartDto dto);
    public List<GetCartDto.Data> getCart(Integer userId); 
}

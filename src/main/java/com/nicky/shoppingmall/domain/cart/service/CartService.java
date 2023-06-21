package com.nicky.shoppingmall.domain.cart.service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.userDetails.MyUserDetails;
import com.nicky.shoppingmall.domain.cart.dto.AddCartDto;
import com.nicky.shoppingmall.domain.cart.dto.ModifyCartDto;
import com.nicky.shoppingmall.domain.cart.dto.RemoveCartDto;

public interface CartService {
    public Response addCart(AddCartDto.Request request, MyUserDetails myUserDetails) throws Exception;    
    public Response removeCart(RemoveCartDto.Request request, MyUserDetails myUserDetails) throws Exception;    
    public Response ModifyCart(ModifyCartDto.Request request, MyUserDetails myUserDetails) throws Exception;    
    public Response getCart(MyUserDetails myUserDetails) throws Exception;    

}

package com.nicky.shoppingmall.domain.cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.business.BusinessException;
import com.nicky.shoppingmall.config.error.ErrorInfo;
import com.nicky.shoppingmall.config.userDetails.MyUserDetails;
import com.nicky.shoppingmall.domain.cart.dto.AddCartDto;
import com.nicky.shoppingmall.domain.cart.dto.GetCartDto;
import com.nicky.shoppingmall.domain.cart.dto.ModifyCartDto;
import com.nicky.shoppingmall.domain.cart.dto.RemoveCartDto;
import com.nicky.shoppingmall.domain.cart.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;

    @Override
    public Response addCart(AddCartDto.Request request, MyUserDetails myUserDetails) throws Exception {
        if(myUserDetails == null) throw new BusinessException(ErrorInfo.ACCESS_UNAUTHORIZED);

        cartMapper.addCart(AddCartDto.builder()
                                        .userId(myUserDetails.getId())
                                        .productId(request.getProductId())
                                        .productOptionId(request.getProductOptionId())
                                        .cnt(request.getCnt())
                                        .build());
        return new Response();
    }

    @Override
    public Response removeCart(RemoveCartDto.Request request, MyUserDetails myUserDetails) throws Exception {
        if(myUserDetails == null) throw new BusinessException(ErrorInfo.ACCESS_UNAUTHORIZED);

        cartMapper.removeCart(new RemoveCartDto(request.getId(), myUserDetails.getId()));
        
        return new Response();
    }

    @Override
    public Response ModifyCart(ModifyCartDto.Request request, MyUserDetails myUserDetails) throws Exception {
        if(myUserDetails == null) throw new BusinessException(ErrorInfo.ACCESS_UNAUTHORIZED);

        cartMapper.modifyCart(new ModifyCartDto(request.getId(), myUserDetails.getId(), request.getCnt()));
        
        return new Response(new ModifyCartDto.Response(request.getCnt()));
    }

    @Override
    public Response getCart(MyUserDetails myUserDetails) throws Exception {
        if(myUserDetails == null) throw new BusinessException(ErrorInfo.ACCESS_UNAUTHORIZED);

        List<GetCartDto.Data> list = cartMapper.getCart(myUserDetails.getId());
        
        return new Response(new GetCartDto.Resonse(list));
    }
    
}

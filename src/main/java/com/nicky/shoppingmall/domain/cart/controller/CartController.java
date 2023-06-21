package com.nicky.shoppingmall.domain.cart.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nicky.shoppingmall.config.Constant;
import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.userDetails.MyUserDetails;
import com.nicky.shoppingmall.domain.cart.dto.AddCartDto;
import com.nicky.shoppingmall.domain.cart.dto.ModifyCartDto;
import com.nicky.shoppingmall.domain.cart.dto.RemoveCartDto;
import com.nicky.shoppingmall.domain.cart.service.CartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping(Constant.API_VER1 + "/cart")
    public Response addCart(@RequestBody @Valid AddCartDto.Request request, @AuthenticationPrincipal MyUserDetails myUserDetails) throws Exception {
        return cartService.addCart(request, myUserDetails);
    }

    @PostMapping(Constant.API_VER1 + "/cart/remove")
    public Response removeCart(@RequestBody @Valid RemoveCartDto.Request request, @AuthenticationPrincipal MyUserDetails myUserDetails) throws Exception {
        return cartService.removeCart(request, myUserDetails);
    }

    @PatchMapping(Constant.API_VER1 + "/cart")
    public Response modifyCart(@RequestBody @Valid ModifyCartDto.Request request, @AuthenticationPrincipal MyUserDetails myUserDetails) throws Exception {
        return cartService.ModifyCart(request, myUserDetails);
    }

    @GetMapping(Constant.API_VER1 + "/cart")
    public Response getCart(@AuthenticationPrincipal MyUserDetails myUserDetails) throws Exception {
        return cartService.getCart(myUserDetails);
    }
}

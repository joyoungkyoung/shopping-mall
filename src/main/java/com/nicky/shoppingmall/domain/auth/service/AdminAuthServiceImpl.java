package com.nicky.shoppingmall.domain.auth.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.business.BusinessException;
import com.nicky.shoppingmall.config.error.ErrorInfo;
import com.nicky.shoppingmall.config.jwt.JwtToken;
import com.nicky.shoppingmall.config.jwt.JwtTokenProvider;
import com.nicky.shoppingmall.domain.auth.dto.ChangeRefreshTokenDto;
import com.nicky.shoppingmall.domain.auth.dto.ReqAdminLogin;
import com.nicky.shoppingmall.domain.auth.dto.ResLogin;
import com.nicky.shoppingmall.domain.auth.mapper.AdminAuthMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {
    private final AdminAuthMapper adminAuthMapper;

    @Qualifier("adminAuthenticationProvider")
    private final AuthenticationProvider provider;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Response login(ReqAdminLogin request) throws Exception {
        String invalidParameter = request.invalidBlank();
        if(invalidParameter != null) {
            throw new BusinessException(ErrorInfo.INCLUDE_BLANK_PARAMETER.getCode(), ErrorInfo.INCLUDE_BLANK_PARAMETER.replaceMessage(invalidParameter));
        }

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            Authentication authentication = provider.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            JwtToken token = jwtTokenProvider.generateToken(authentication);
    
            adminAuthMapper.changeRefreshToken(new ChangeRefreshTokenDto(request.getUsername(), token.getRefreshToken()));
    
            ResLogin resLogin = new ResLogin(token.getAccessToken(), token.getRefreshToken());
            return new Response(resLogin);
        } catch(BadCredentialsException e) {
            throw new BusinessException(ErrorInfo.WRONG_USERNAME_OR_PASSWORD);
        }  
    }
    
}

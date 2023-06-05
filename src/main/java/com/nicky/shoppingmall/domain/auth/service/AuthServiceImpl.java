package com.nicky.shoppingmall.domain.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.business.BusinessException;
import com.nicky.shoppingmall.config.error.ErrorInfo;
import com.nicky.shoppingmall.config.jwt.JwtToken;
import com.nicky.shoppingmall.config.jwt.JwtTokenProvider;
import com.nicky.shoppingmall.config.util.RegexUtil;
import com.nicky.shoppingmall.domain.auth.dto.RefreshTokenDto;
import com.nicky.shoppingmall.domain.auth.dto.ReqCreateAccount;
import com.nicky.shoppingmall.domain.auth.dto.ReqLogin;
import com.nicky.shoppingmall.domain.auth.mapper.AuthMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Response create(ReqCreateAccount request) throws Exception {
        String invalidParameter = request.invalidBlank();
        if(invalidParameter != null) {
            throw new BusinessException(ErrorInfo.INCLUDE_BLANK_PARAMETER.getCode(), ErrorInfo.INCLUDE_BLANK_PARAMETER.replaceMessage(invalidParameter));
        }

        boolean isEmail = RegexUtil.regexEmail(request.getEmail());
        if(!isEmail) {
            throw new BusinessException(ErrorInfo.INVALID_REGEX_EMAIL);
        }

        UserDetails user = authMapper.getUserDetails(request.getEmail());
        if(user != null) {
            throw new BusinessException(ErrorInfo.ALREADY_EXIST_ACCOUNT);
        }

        Map<String, Object> map = new HashMap<>();

        map.put("email", request.getEmail());
        map.put("nickname", request.getEmail());
        map.put("password", passwordEncoder.encode(request.getPassword()));
        map.put("type", 1);
        map.put("authorityCode", "STAFF");
        map.put("status", 1);
        map.put("isEmailAlertConfirm", 1);

        authMapper.register(map);

        return new Response();
    }

    @Override
    public Response login(ReqLogin request) throws Exception {
        String invalidParameter = request.invalidBlank();
        if(invalidParameter != null) {
            throw new BusinessException(ErrorInfo.INCLUDE_BLANK_PARAMETER.getCode(), ErrorInfo.INCLUDE_BLANK_PARAMETER.replaceMessage(invalidParameter));
        }


        boolean isEmail = RegexUtil.regexEmail(request.getEmail());
        if(!isEmail) {
            throw new BusinessException(ErrorInfo.INVALID_REGEX_EMAIL);
        }

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            JwtToken token = jwtTokenProvider.generateToken(authentication);
    
            Map<String, Object> req = new HashMap<>();
            req.put("email", request.getEmail());
            req.put("refreshToken", token.getRefreshToken());

            authMapper.changeRefreshToken(req);

            Response response = new Response();
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("accessToken", token.getAccessToken());
            mapData.put("refreshToken", token.getRefreshToken());

            response.setData(mapData);
    
            return response;
        } catch(BadCredentialsException e) {
            throw new BusinessException(ErrorInfo.WRONG_USERNAME_OR_PASSWORD);
        }  
    }

    @Override
    public Response refresh(HttpServletRequest request, String refreshToken) throws Exception {
        // 만료된 accessToken 반환
        String accessToken = jwtTokenProvider.resolveToken(request);
        if(accessToken != null && !jwtTokenProvider.validateToken(accessToken)){
            RefreshTokenDto dto = authMapper.getByRefreshToken(refreshToken);

            if(dto == null) {
                throw new BusinessException(ErrorInfo.NOT_FOUND_USER_DATA);
            }

            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken); 

            if(!dto.getEmail().equals(authentication.getName())){
                throw new BusinessException(ErrorInfo.INVALID_TOKEN);
            }
            JwtToken newToken = jwtTokenProvider.generateToken(authentication);

            Map<String, Object> req = new HashMap<>();
            req.put("email", dto.getEmail());
            req.put("refreshToken", newToken.getRefreshToken());
            authMapper.changeRefreshToken(req);

            Response response = new Response();
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("accessToken", newToken.getAccessToken());
            mapData.put("refreshToken", newToken.getRefreshToken());

            response.setData(mapData);
    
            return response;
        }else {
            throw new BusinessException(ErrorInfo.INVALID_TOKEN);
        }
    }
}

package com.nicky.shoppingmall.domain.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.business.BusinessException;
import com.nicky.shoppingmall.config.error.ErrorInfo;
import com.nicky.shoppingmall.config.jwt.JwtToken;
import com.nicky.shoppingmall.config.jwt.JwtTokenProvider;
import com.nicky.shoppingmall.config.util.RegexUtil;
import com.nicky.shoppingmall.domain.user.dto.ReqCreateAccount;
import com.nicky.shoppingmall.domain.user.dto.ReqLogin;
import com.nicky.shoppingmall.domain.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {
    private final UserMapper userMapper;
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

        UserDetails user = userMapper.getUserDetails(request.getEmail());
        if(user != null) {
            throw new BusinessException(ErrorInfo.ALREADY_EXIST_ACCOUNT);
        }

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("email", request.getEmail());
        map.put("nickname", request.getEmail());
        map.put("password", passwordEncoder.encode(request.getPassword()));
        map.put("type", 1);
        map.put("userLevelId", 1);
        map.put("status", 1);
        map.put("isEmailAlertConfirm", 1);

        userMapper.register(map);

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
    
            JwtToken token = jwtTokenProvider.generateToken(authentication);
    
            Map<String, Object> req = new HashMap<>();
            req.put("email", request.getEmail());
            req.put("refreshToken", token.getRefreshToken());

            userMapper.changeRefreshToken(req);

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
    
}

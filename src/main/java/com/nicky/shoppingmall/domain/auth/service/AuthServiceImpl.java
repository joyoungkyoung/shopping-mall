package com.nicky.shoppingmall.domain.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nicky.shoppingmall.config.Response;
import com.nicky.shoppingmall.config.business.BusinessException;
import com.nicky.shoppingmall.config.error.ErrorInfo;
import com.nicky.shoppingmall.config.jwt.JwtToken;
import com.nicky.shoppingmall.config.jwt.JwtTokenProvider;
import com.nicky.shoppingmall.config.util.RegexUtil;
import com.nicky.shoppingmall.domain.auth.dto.RefreshTokenDto;
import com.nicky.shoppingmall.domain.auth.dto.CreateUserDto;
import com.nicky.shoppingmall.domain.auth.dto.LoginDto;
import com.nicky.shoppingmall.domain.auth.mapper.AuthMapper;
import com.nicky.shoppingmall.domain.user.dto.CreateNewAddressDto;
import com.nicky.shoppingmall.domain.user.dto.ModifyAddressIdDto;
import com.nicky.shoppingmall.domain.user.mapper.AddressMapper;
import com.nicky.shoppingmall.domain.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthMapper authMapper;
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;
    @Qualifier("userAuthenticationProvider")
    private final AuthenticationProvider provider;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response create(CreateUserDto.Request request) throws Exception {
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

        // 유저정보 저장
        CreateUserDto registerDto = CreateUserDto.builder()
                                            .email(request.getEmail())
                                            .nickname(request.getNickname())
                                            .password(passwordEncoder.encode(request.getPassword()))
                                            .type(1)
                                            .authorityCode("STAFF")
                                            .status(1)
                                            .isEmailAlertConfirm(request.getIsEmailAlertConfirm())
                                            .phone(request.getPhone().replaceAll("-", ""))
                                            .build();
        authMapper.createUser(registerDto);

        
        // 주소지 저장
        CreateNewAddressDto addNewAddressDto = CreateNewAddressDto.builder()
                                                .userId(registerDto.getId())
                                                .zipCode(request.getZipCode())
                                                .addressMain(request.getAddressMain())
                                                .addressSub(request.getAddressSub()).build();
        addressMapper.createNewAddress(addNewAddressDto);

        // 주소지 메인 적용
        userMapper.modifyAddressId(ModifyAddressIdDto.builder()
                                                    .id(registerDto.getId())
                                                    .addressId(addNewAddressDto.getId())
                                                    .build());

        return new Response();
    }

    @Override
    public Response login(LoginDto.Request request) throws Exception {
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
            Authentication authentication = provider.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            JwtToken token = jwtTokenProvider.generateToken(authentication);
    
            Map<String, Object> req = new HashMap<>();
            req.put("email", request.getEmail());
            req.put("refreshToken", token.getRefreshToken());

            authMapper.modifyRefreshToken(req);
    
            return new Response(new LoginDto.Response(token.getAccessToken(), token.getRefreshToken()));
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

            if(!dto.getUsername().equals(authentication.getName())){
                throw new BusinessException(ErrorInfo.INVALID_TOKEN);
            }
            JwtToken newToken = jwtTokenProvider.generateToken(authentication);

            Map<String, Object> req = new HashMap<>();
            req.put("email", dto.getUsername());
            req.put("refreshToken", newToken.getRefreshToken());
            authMapper.modifyRefreshToken(req);
    
            return new Response(new LoginDto.Response(newToken.getAccessToken(), newToken.getRefreshToken()));
        }else {
            throw new BusinessException(ErrorInfo.INVALID_TOKEN);
        }
    }
}

package com.nicky.shoppingmall.config.userDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nicky.shoppingmall.domain.auth.mapper.AdminAuthMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {
    private final AdminAuthMapper adminAuthMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = adminAuthMapper.getUserDetails(username);
        
        if(user == null) {
            log.info("데이터 없음");
            throw new UsernameNotFoundException("User Not Found.");
        }
        return user;
    }
    
}

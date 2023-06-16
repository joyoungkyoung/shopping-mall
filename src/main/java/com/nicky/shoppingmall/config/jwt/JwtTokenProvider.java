package com.nicky.shoppingmall.config.jwt;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.nicky.shoppingmall.config.userDetails.AdminDetailsService;
import com.nicky.shoppingmall.config.userDetails.MyUserDetailsService;
import com.nicky.shoppingmall.domain.auth.mapper.AdminAuthMapper;
import com.nicky.shoppingmall.domain.auth.mapper.AuthMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;
    private final int EXPIRE_ACCESS_TOKEN_TIME = 1000 * 60 * 60;
    private final int EXPIRE_REFRESH_TOKEN_TIME = 1000 * 60 * 60 * 36;

    private final AdminDetailsService adminDetailsService;
    private final MyUserDetailsService myUserDetailsService;
    

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, AdminDetailsService adminDetailsService, MyUserDetailsService myUserDetailsService) {
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        this.key = Keys.hmacShaKeyFor(secretByteKey);
        this.adminDetailsService = adminDetailsService;
        this.myUserDetailsService = myUserDetailsService;
    }

    public JwtToken generateToken(Authentication authentication) {
        log.info(null, authentication); 
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        String accessToken = Jwts.builder()
                                .setSubject(authentication.getName())
                                .claim("auth", authorities)
                                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_ACCESS_TOKEN_TIME))
                                .signWith(key, SignatureAlgorithm.HS256)
                                .compact();
        String refreshToken = Jwts.builder()
                                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_REFRESH_TOKEN_TIME))
                                .signWith(key, SignatureAlgorithm.HS256)
                                .compact();

        return JwtToken.builder().grantType("Bearer").accessToken(accessToken).refreshToken(refreshToken).build();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        String authotiry = claims.get("auth").toString().split(",")[0];
        UserDetailsService service = adminDetailsService;
        if(authotiry.equals("ROLE_CUSTOMER")) {
            service = myUserDetailsService;
        }
        UserDetails userDetails = service.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty");
        }

        return false;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch(ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}

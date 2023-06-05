package com.nicky.shoppingmall.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nicky.shoppingmall.config.jwt.JwtAuthenticationFilter;
import com.nicky.shoppingmall.config.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // 사이트간 위조요청 방지기능 disable : 서버에서 인증정보를 저장하지 않으므로 작성하지 않음
            .cors().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt를 사용하므로 세션 사용하지 않음
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling(request -> request
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
            )
            .authorizeHttpRequests(request -> request
                .requestMatchers("/css/**", "/js/**", "/image/**").permitAll()
                .requestMatchers("/api/v1/auth/**", "/login", "/").permitAll()
                .requestMatchers("/test3").authenticated()
                .requestMatchers("/test").hasAuthority("ROLE_STAFF")
                .requestMatchers("/test2").hasAuthority("ROLE_OWNER")
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
            )
            // UsernamePasswordAuthenticationFilter 처리 전에, JwtAuthenticationFilter 를 거침
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
            ;

        return http.build();
    }
}
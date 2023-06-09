package com.nicky.shoppingmall.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nicky.shoppingmall.config.jwt.JwtAuthenticationFilter;
import com.nicky.shoppingmall.config.jwt.JwtTokenProvider;
import com.nicky.shoppingmall.config.userDetails.AdminDetailsService;
import com.nicky.shoppingmall.config.userDetails.MyUserDetailsService;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final JwtTokenProvider jwtTokenProvider;
    
    private final MyUserDetailsService myUserDetailsService;
    private final AdminDetailsService adminDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        
        return provider;
    }

    @Bean
    public DaoAuthenticationProvider adminAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        
        return provider;
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
                .requestMatchers("/", "/login", "/signup", "/product-detail").permitAll()
                .requestMatchers("/api/v1/auth/**", "/api/v1/image/**").permitAll()
                .requestMatchers("/api/v1/product/**").permitAll()
                .requestMatchers("/api/v1/cart/**").hasAnyAuthority("ROLE_CUSTOMER")
                .requestMatchers("/api/v1/image/product").authenticated()
                
                .requestMatchers("/admin", "/admin/**", "/api/v1/admin/auth/**").permitAll()
                .requestMatchers("/api/v1/admin/**").hasAnyAuthority("ROLE_STAFF", "ROLE_VIEWER", "ROLE_OWNER")
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
            )
            // UsernamePasswordAuthenticationFilter 처리 전에, JwtAuthenticationFilter 를 거침
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
            ;

        return http.build();
    }
}
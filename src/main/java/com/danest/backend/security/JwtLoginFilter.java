package com.danest.backend.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        super(authenticationManager);
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        System.out.println("aplicando el filter de login...");
        String jwt = jwtTokenUtil
                .generateToken(User.withUsername(authResult.getName()).password("")
                        .authorities(
                                List.of())
                        .build());
        Cookie authorizationCookie = new Cookie("Authorization", jwt);
        authorizationCookie.setHttpOnly(true);
        authorizationCookie.setSecure(true);
        authorizationCookie.setAttribute("SameSite", "None");
        response.addCookie(authorizationCookie);
        SecurityContextHolder.getContext().setAuthentication(authResult);
    }

}

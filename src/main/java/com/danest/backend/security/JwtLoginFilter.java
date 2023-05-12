package com.danest.backend.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenUtil jwtTokenUtil;

    JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        super(authenticationManager);
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        System.out.println("alguien se logueo correctamente");
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

    /*
     * @Override
     * protected void doFilterInternal(HttpServletRequest request,
     * HttpServletResponse response, FilterChain filterChain)
     * throws ServletException, IOException {
     * String username = request.getParameter("username");
     * String password = request.getParameter("password");
     * 
     * Authentication authentication = new
     * UsernamePasswordAuthenticationToken(username, password);
     * try {
     * authenticationManager.authenticate(authentication);
     * } catch (AuthenticationException failure) {
     * response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
     * failure.getMessage());
     * return;
     * }
     * String jwt = jwtTokenUtil
     * .generateToken(User.withUsername(username).password(password).authorities(
     * List.of()).build());
     * Cookie authorizationCookie = new Cookie("Authorization", jwt);
     * authorizationCookie.setHttpOnly(true);
     * authorizationCookie.setSecure(true);
     * authorizationCookie.setAttribute("SameSite", "None");
     * response.addCookie(authorizationCookie);
     * return;
     * }
     * 
     * @Override
     * protected boolean shouldNotFilter(HttpServletRequest request) {
     * return !request.getServletPath().equals("/login");
     * }
     */

}

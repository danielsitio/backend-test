package com.danest.backend.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.danest.backend.security.JwtAuthorizationFilter;
import com.danest.backend.security.JwtLoginFilter;
import com.danest.backend.security.JwtTokenUtil;

@Configuration
public class SecurityConfig {

        private final AuthenticationManager authenticationManager;
        private final JwtTokenUtil jwtTokenUtil;

        SecurityConfig(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
                this.authenticationManager = authenticationManager;
                this.jwtTokenUtil = jwtTokenUtil;
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http
                                .formLogin(formlogin -> formlogin
                                                .disable())
                                .csrf(csrf -> csrf
                                                .disable())

                                .cors(cors -> cors
                                                .configurationSource(corsConfigurationSource()))

                                .addFilterAt(new JwtLoginFilter(authenticationManager, jwtTokenUtil),
                                                UsernamePasswordAuthenticationFilter.class)

                                .addFilterAt(new JwtAuthorizationFilter(jwtTokenUtil),
                                                UsernamePasswordAuthenticationFilter.class)

                                .sessionManagement(sessionManagement -> sessionManagement
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(
                                                                HttpStatus.OK))
                                                .deleteCookies("Authorization"));
                return http.build();
        }

        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.addAllowedOriginPattern("*");
                corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
                corsConfiguration.setExposedHeaders(Arrays.asList("*"));
                corsConfiguration.setAllowCredentials(true);
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", corsConfiguration);
                return source;
        }

}

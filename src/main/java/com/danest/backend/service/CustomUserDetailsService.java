package com.danest.backend.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.danest.backend.domain.User;
import com.danest.backend.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userFound = userRepository.findByUsername(username);

        if (userFound.isPresent()) {

            User user = userFound.get();
            System.out.print("el usuario encontrado es " + user.getUsername());
            System.out.println("con contraseña " + user.getPassword());
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities("admin")
                    .build();
        }
        throw new UsernameNotFoundException("Username not found.");
    }

}

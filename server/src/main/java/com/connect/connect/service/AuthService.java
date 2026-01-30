package com.connect.connect.service;

import com.connect.connect.model.User;
import com.connect.connect.repository.UserRepository;
import com.connect.connect.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public Map<String, Object> register(String username, String email, String password) {
        if (userRepository.existsByEmail(email)) {
             throw new RuntimeException("Email already taken");
        }
        var user = new User(username, email, passwordEncoder.encode(password));
        userRepository.save(user);

        var userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();

        var jwtToken = jwtService.generateToken(userDetails);
        return Map.of("token", jwtToken, "user", user);
    }

    public Map<String, Object> login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        var user = userRepository.findByEmail(email).orElseThrow();
        
        var userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();

        var jwtToken = jwtService.generateToken(userDetails);
        return Map.of("token", jwtToken, "user", user);
    }
}

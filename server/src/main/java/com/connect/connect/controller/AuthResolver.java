package com.connect.connect.controller;

import com.connect.connect.service.AuthService;
import com.connect.connect.model.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class AuthResolver {

    private final AuthService authService;

    public AuthResolver(AuthService authService) {
        this.authService = authService;
    }

    @MutationMapping
    public Map<String, Object> register(@Argument RegisterInput input) {
        return authService.register(input.username, input.email, input.password);
    }

    @MutationMapping
    public Map<String, Object> login(@Argument LoginInput input) {
        return authService.login(input.email, input.password);
    }

    record RegisterInput(String username, String email, String password) {}
    record LoginInput(String email, String password) {}
}

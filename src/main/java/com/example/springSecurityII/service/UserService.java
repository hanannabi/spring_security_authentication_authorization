package com.example.springSecurityII.service;

import com.example.springSecurityII.dto.UserRequest;
import com.example.springSecurityII.dto.UserResponse;
import com.example.springSecurityII.entity.Users;
import com.example.springSecurityII.repository.UserDetailsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse create(UserRequest request) {
        if (userDetailsRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("User Already exists");
        }
        Users users = new Users();
        users.setUsername(request.getUsername());
        users.setPassword(passwordEncoder.encode(request.getPassword()));
        users.setRole(request.getRole());
        Users savedUser = userDetailsRepository.save(users);
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getRole().name());
    }
}

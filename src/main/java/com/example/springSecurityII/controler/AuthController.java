package com.example.springSecurityII.controler;

import com.example.springSecurityII.entity.AuthRequest;
import com.example.springSecurityII.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//First task
/// 1.implement Authenticate api
/// 2. authenticate user
/// 3. generate token , that client will use for subsequent requests
//Second task

///implement JWT Authentication Filter
/// Logic to validate token
@RestController
@RequestMapping("/home")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw e;
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }
}

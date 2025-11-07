package com.example.springSecurityII.controler;

import com.example.springSecurityII.dto.UserRequest;
import com.example.springSecurityII.dto.UserResponse;
import com.example.springSecurityII.entity.Role;
import com.example.springSecurityII.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //self registration, by making this api public in secConfig
    @PostMapping("/register")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        request.setRole(Role.USER);
        UserResponse userResponse = userService.create(request);
        return ResponseEntity.ok(userResponse);
    }

    //only admin creates many more admins ,users...using @PreAuth orize
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/create")
    public ResponseEntity<UserResponse> registerByAdmin(@RequestBody UserRequest request){
        UserResponse userResponse = userService.create(request);
        return ResponseEntity.ok(userResponse);
    }
}

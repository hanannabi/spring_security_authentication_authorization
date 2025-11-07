package com.example.springSecurityII.service;

import com.example.springSecurityII.entity.Role;
import com.example.springSecurityII.entity.Users;
import com.example.springSecurityII.repository.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {


    @Bean
    public CommandLineRunner createAdminUser(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userDetailsRepository.findByUsername("admin").isEmpty()) {
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRole(Role.ADMIN);
                userDetailsRepository.save(admin);
                System.out.println("default admin user");
            }
            if (userDetailsRepository.findByUsername("user").isEmpty()) {
                Users admin = new Users();
                admin.setUsername("user");
                admin.setPassword(passwordEncoder.encode("user1234"));
                admin.setRole(Role.USER);
                userDetailsRepository.save(admin);
                System.out.println("default admin user");
            }

        };

    }
}



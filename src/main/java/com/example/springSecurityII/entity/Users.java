package com.example.springSecurityII.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//📌 What is an Entity?
//In Java Persistence API (JPA), an Entity is a lightweight, persistent domain object. This means:
//It represents a table in the database.
//Each instance of the entity class represents a row in the table.
//The fields of the entity class represent the columns of that table

///🧱 What Can We Do With This Class?
//1. Persistence (Database Storage)
    //You can save, retrieve, update, and delete Users in the database using JPA repositories:
    //Users user = new Users();
    //user.setUsername("john");
    //user.setPassword("1234");
    //user.setRole("USER");
    //usersRepository.save(user);  // this inserts the user into the DB
 //2. Authentication and Authorization
    //This class often integrates with Spring Security to handle login and access control:
    //username and password for login.
    //role for authorizing actions (e.g., only admins can access a certain endpoint).

    ///✅ Summary
    /// The Users class is a JPA Entity used to map a Java object to a database table row. With it, you can:
    /// Store and retrieve user data
    /// Use it in authentication systems
    /// Build APIs that allow front-end applications to interact with user data
    ///
@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)   //want this converted into string to save in database
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

        @Override
        public boolean isAccountNonExpired() {
            return UserDetails.super.isAccountNonExpired();
        }

        @Override
        public boolean isAccountNonLocked() {
            return UserDetails.super.isAccountNonLocked();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return UserDetails.super.isCredentialsNonExpired();
        }

        @Override
        public boolean isEnabled() {
            return UserDetails.super.isEnabled();
        }

        public void setUsername(String username) {
        this.username = username;
    }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {  //This tells Spring Security: “What roles/permissions does this user have?”
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            //add role to authorities
            authorities.add(new SimpleGrantedAuthority("ROLE_" +role.name()));
            //add permissions to authorities by extracting it from role
            authorities.addAll(role.getPermissions().stream()
                    .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                    .collect(Collectors.toSet()));
            return authorities;
        }

        public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }
    }

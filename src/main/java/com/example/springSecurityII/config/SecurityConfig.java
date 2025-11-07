package com.example.springSecurityII.config;

import com.example.springSecurityII.entity.Permissions;
import com.example.springSecurityII.entity.Role;
import com.example.springSecurityII.filters.JwtAuthFilter;
import com.example.springSecurityII.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableWebSecurity: Enables Spring Security and tells Spring to apply custom security configurations instead of the default
public class SecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.
                    csrf(csrf -> csrf.disable()).
                    authorizeHttpRequests(auth ->
                            auth.requestMatchers("/home/authenticate").permitAll()  ///authenticate api will not come under basic authentication
                                     .requestMatchers("/api/user/register").permitAll() ///authenticate api will not come under basic authentication
//                                     .requestMatchers("/weather/health").hasRole("ADMIN") ///authenticate api will not come under basic authentication
//                                     .requestMatchers("/weather/health").hasRole(Role.ADMIN.name()) //one of same thing as above , here we have used enum
//                                    .requestMatchers(HttpMethod.POST,"/weather/add").hasAuthority(Permissions.WEATHER_WRITE.name()) //request matcher delegating authorization manager, assigning role to a particular api/request
                                    .requestMatchers(HttpMethod.GET,"/weather/**").hasAuthority(Permissions.WEATHER_READ.name()) //all the http.get method with this pattern will be accessed by roles (ADMIN,USER) with permission WEATHER_READ, so these apis will be accessed by both User and Admin because WEATHER_PERMISSION is in both user and admin roles.
                                    .requestMatchers(HttpMethod.DELETE,"/weather/delete").hasAuthority(Permissions.WEATHER_DELETE.name())
//                                    .requestMatchers(HttpMethod.DELETE,"/weather/**").hasAuthority(Permissions.WEATHER_DELETE.name()) //request matcher delegating authorization manager, assigning role to a particular api/request
                                    .anyRequest().authenticated());
            http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }
}

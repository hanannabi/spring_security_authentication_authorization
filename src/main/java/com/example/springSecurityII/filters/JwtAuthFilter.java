package com.example.springSecurityII.filters;

import com.example.springSecurityII.service.CustomUserDetailsService;
import com.example.springSecurityII.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //extract bearer token from request, which is in authorization header
        String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
             username = jwtUtil.extractUsernameFromToken(token);
        }
        //checking this username is present in context holder and not null and VALIDATING
        if (username!= null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            //if token is valid add it to security context holder
            if (jwtUtil.validateToken(username,userDetails,token)){
                //create authtoken/pinciple object/userdetails
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                //setting more details to token related to the request
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //set
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}

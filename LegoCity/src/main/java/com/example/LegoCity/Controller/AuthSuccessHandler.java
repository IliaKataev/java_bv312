package com.example.LegoCity.Controller;

import com.example.LegoCity.Models.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        boolean isAdmin = false;
        boolean isUser = false;

        for(GrantedAuthority auth : authentication.getAuthorities()){
            if(Objects.equals(auth.getAuthority(), "ROLE_ADMIN")) isAdmin = true;
            if(Objects.equals(auth.getAuthority(), "ROLE_USER") )isUser = true;
            String role = auth.getAuthority();
            System.out.println(role + " это роль");
        }

        if(isAdmin){
            response.sendRedirect("/admin");
        } else if (isUser){
            response.sendRedirect("/city");
        } else {
            response.sendRedirect("/login?error");
        }
    }
}

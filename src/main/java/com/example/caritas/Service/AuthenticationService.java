package com.example.caritas.Service;

import com.example.caritas.Security.CostumUserDetails;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails authenticate(String username, String password);
    String generateToken(CostumUserDetails userDetails);
    UserDetails validateToken(String token);
}

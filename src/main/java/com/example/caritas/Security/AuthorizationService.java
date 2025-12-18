package com.example.caritas.Security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("authz")
public class AuthorizationService {
    public boolean canAccessMagazzino(Authentication auth, UUID magazzinoId) {

        if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }

        Object principal = auth.getPrincipal();
        if (principal instanceof CostumUserDetails user) {
            return user.getMagazzini().contains(magazzinoId);
        }
        return false;
    }
}

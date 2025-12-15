package com.example.caritas.Security;

import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Entity.User;
import com.example.caritas.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CostumUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public CostumUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        Set<UUID> magazziniUUID = user.getMagazzini().stream()
                .map(m -> m.getId())
                .collect(Collectors.toSet());
        return new CostumUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole().toString(),
                magazziniUUID
        );
    }
}

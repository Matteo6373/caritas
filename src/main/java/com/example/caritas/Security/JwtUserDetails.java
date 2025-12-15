package com.example.caritas.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {

    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
    private final List<String> magazzini;

    public JwtUserDetails(String username,
                          Collection<? extends GrantedAuthority> authorities,
                          List<String> magazzini) {
        this.username = username;
        this.authorities = authorities;
        this.magazzini = magazzini;
    }

    public List<String> getMagazzini() {
        return magazzini;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override public String getPassword() { return null; }
    @Override public String getUsername() { return username; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}

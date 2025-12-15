package com.example.caritas.Service;

import com.example.caritas.Entity.User;

import java.util.Set;
import java.util.UUID;

public interface UserService {
    User getUserByUsername(String username);
    User getUserById(UUID uuid);
    Set<UUID> getMagazziniUUIDperUser(UUID uuid);
}

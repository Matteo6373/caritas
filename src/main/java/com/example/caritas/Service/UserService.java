package com.example.caritas.Service;

import com.example.caritas.Dto.UserRequestDto;
import com.example.caritas.Dto.UserResponseDto;
import com.example.caritas.Entity.User;

import java.util.Set;
import java.util.UUID;

public interface UserService {
    User getUserByUsername(String username);
    User getUserById(UUID uuid);
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto deleteUser(UUID uuid);
    UserResponseDto associaMagazzino(UUID UserID,UUID magazzinoUUID);
    UserResponseDto rimuoviMagazzino(UUID UserID,UUID magazzinoUUID);
}

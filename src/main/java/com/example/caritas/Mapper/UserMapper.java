package com.example.caritas.Mapper;

import com.example.caritas.Dto.GiacenzaResponseDto;
import com.example.caritas.Dto.UserRequestDto;
import com.example.caritas.Dto.UserResponseDto;
import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Entity.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setRole(user.getRole());
        Set<Magazzino> magazzini = user.getMagazzini();
        if(magazzini!= null && !magazzini.isEmpty()){
            Set<UUID> magazziniUUIDs = magazzini.stream()
                    .map(Magazzino::getId).collect(Collectors.toSet());
            userResponseDto.setMagazzini(magazziniUUIDs);
        }else {
            userResponseDto.setMagazzini(new HashSet<>());
        }
        userResponseDto.setId(user.getId());
        return userResponseDto;
    }
    public static User toEntity(UserRequestDto userRequestDto, Set<Magazzino> magazzini) {
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(userRequestDto.getPassword());
        user.setRole(userRequestDto.getRole());
        user.setMagazzini(magazzini);
        return user;
    }
}

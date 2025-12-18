package com.example.caritas.Controller;

import com.example.caritas.Dto.IdDto;
import com.example.caritas.Dto.UserRequestDto;
import com.example.caritas.Dto.UserResponseDto;
import com.example.caritas.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable UUID id){
        UserResponseDto userResponseDto = userService.deleteUser(id);
        return ResponseEntity.ok().body(userResponseDto);
    }
    @PostMapping("/{id}/magazzino")
    public ResponseEntity<UserResponseDto> aggiungiMagazzino(
            @PathVariable UUID id, @RequestBody IdDto idDtoMagazzino){
        UserResponseDto userResponseDto = userService.associaMagazzino(id,idDtoMagazzino.getId());
        return ResponseEntity.ok().body(userResponseDto);
    }
    @DeleteMapping("/{id}/magazzino")
    public ResponseEntity<UserResponseDto> togliMagazzino(
            @PathVariable UUID id,@RequestBody IdDto idDtoMagazzino) {
        UserResponseDto userResponseDto = userService.rimuoviMagazzino(id,idDtoMagazzino.getId());
        return ResponseEntity.ok(userResponseDto);
    }

}

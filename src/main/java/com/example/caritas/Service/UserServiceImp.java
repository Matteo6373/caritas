package com.example.caritas.Service;

import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Entity.User;
import com.example.caritas.Repository.MagazzinoRepository;
import com.example.caritas.Repository.UserRepository;
import com.example.caritas.exception.NullException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final MagazzinoRepository magazzinoRepository;

    @Override
    public User getUserByUsername(String username) {
        if(username == null) {
            throw new NullException("username is null");
        }
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new NullException("User non trovato"));
    }

    @Override
    public User getUserById(UUID uuid) {
        if(uuid == null) {
            throw new NullException("id of user is null");
        }
        return userRepository.findById(uuid).orElseThrow(()-> new NullException("User non trovato"));
    }

    @Override
    public Set<UUID> getMagazziniUUIDperUser(UUID uuid) {
        return magazzinoRepository.findMagazziniIdByUserId(uuid);
    }
}

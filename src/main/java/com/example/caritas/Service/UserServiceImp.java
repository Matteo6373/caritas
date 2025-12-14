package com.example.caritas.Service;

import com.example.caritas.Entity.User;
import com.example.caritas.Repository.UserRepository;
import com.example.caritas.exception.NullException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        if(username == null) {
            throw new NullException("username is null");
        }
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new NullException("User non trovato"));
    }
}

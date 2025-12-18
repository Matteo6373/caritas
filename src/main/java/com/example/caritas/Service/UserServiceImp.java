package com.example.caritas.Service;

import com.example.caritas.Dto.UserRequestDto;
import com.example.caritas.Dto.UserResponseDto;
import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Entity.Prodotto;
import com.example.caritas.Entity.User;
import com.example.caritas.Mapper.ProdottoMapper;
import com.example.caritas.Mapper.UserMapper;
import com.example.caritas.Repository.MagazzinoRepository;
import com.example.caritas.Repository.UserRepository;
import com.example.caritas.exception.AlreadyExistsByNomeException;
import com.example.caritas.exception.DeleteException;
import com.example.caritas.exception.NullException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MagazzinoService magazzinoService;

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
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = UserMapper.toEntity(userRequestDto,null);
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsByNomeException(
                    "A User with this username already exists:"
                            + userRequestDto.getUsername());

        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userReturned = userRepository.save(user);
        return UserMapper.toDto(userReturned);
    }

    @Override
    public UserResponseDto deleteUser(UUID uuid) {
        User user = getUserById(uuid);
        try{
            userRepository.delete(user);
            return UserMapper.toDto(user);
        }catch(DataIntegrityViolationException e){
            throw new DeleteException(
                    "can't delete user with id:"+uuid + " because exists in a magazine"
            );
        }
    }

    @Override
    public UserResponseDto associaMagazzino(UUID UserID, UUID magazzinoUUID) {
        User user = getUserById(UserID);
        Magazzino magazzino = magazzinoService.getMagazzino(magazzinoUUID);
        if(!user.getMagazzini().contains(magazzino)) {
            user.getMagazzini().add(magazzino);
            userRepository.save(user);
            return UserMapper.toDto(user);
        }else throw  new NullException("Magazzino gia associato a questo user");

    }

    @Override
    public UserResponseDto rimuoviMagazzino(UUID UserID, UUID magazzinoUUID) {
        User user = getUserById(UserID);
        Magazzino magazzino = magazzinoService.getMagazzino(magazzinoUUID);
        if(user.getMagazzini().contains(magazzino)) {
            user.getMagazzini().remove(magazzino);
            userRepository.save(user);
            return UserMapper.toDto(user);
        }
        else throw new NullException("Magazzino non associato a questo user");
    }

}

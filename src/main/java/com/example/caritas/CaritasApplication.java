package com.example.caritas;

import com.example.caritas.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CaritasApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CaritasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode("admin");
        System.out.println(hashedPassword);
        hashedPassword = encoder.encode("test");
        System.out.println(hashedPassword);

    }
}


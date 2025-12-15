package com.example.caritas.Controller;

import com.example.caritas.Dto.AuthResponse;
import com.example.caritas.Dto.LoginRequest;
import com.example.caritas.Security.CostumUserDetails;
import com.example.caritas.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @Value("${jwt.expiration}")
    private Long expiresIn;
    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            CostumUserDetails userDetails = (CostumUserDetails) authenticationService.authenticate(
                    loginRequest.getUsername(),
                    loginRequest.getPassword());

            String token = authenticationService.generateToken(userDetails);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(token);
            authResponse.setExpiresIn(expiresIn);

            return ResponseEntity.ok(authResponse);

        } catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }

}

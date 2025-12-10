package com.example.caritas.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequestDto {
    @NotBlank(message = "Name is required")
    private String nome;
    private String descrizione;
}

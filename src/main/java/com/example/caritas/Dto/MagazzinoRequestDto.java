package com.example.caritas.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagazzinoRequestDto {
    @NotBlank(message = "Name is required")
    private String nome;
}

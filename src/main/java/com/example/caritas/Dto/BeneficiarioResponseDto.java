package com.example.caritas.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarioResponseDto {
    private String id;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
}

package com.example.caritas.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BeneficiarioRequestDto {
    @NotBlank(message = "Name is required")
    private String nome;
    @NotBlank
    private String cognome;
    @NotBlank
    private LocalDate dataNascita;
    @NotNull(message = "magazzinoId is required")
    private UUID magazzinoId;
}

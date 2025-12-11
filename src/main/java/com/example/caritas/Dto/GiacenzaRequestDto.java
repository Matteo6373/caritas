package com.example.caritas.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiacenzaRequestDto {
    @NotBlank
    private UUID prodottoId;
    @NotBlank
    private Integer quantita;
}

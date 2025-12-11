package com.example.caritas.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiacenzaResponseDto {
    private String id;
    private ProdottoResponseDto prodotto;
    private Integer quantita;
}

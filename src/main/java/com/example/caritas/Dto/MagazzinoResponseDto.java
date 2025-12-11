package com.example.caritas.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagazzinoResponseDto {
    private String id;
    private String nome;
    private Set<GiacenzaResponseDto> giacenze;
}

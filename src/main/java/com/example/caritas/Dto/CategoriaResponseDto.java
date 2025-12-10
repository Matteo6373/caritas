package com.example.caritas.Dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDto {
    private String id;
    private String nome;
    private String descrizione;
}

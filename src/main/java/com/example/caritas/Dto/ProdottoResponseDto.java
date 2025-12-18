package com.example.caritas.Dto;



import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdottoResponseDto {
    private String id;
    private String nome;
    private String descrizione;
    private CategoriaResponseDto categoria;
}

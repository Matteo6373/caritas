package com.example.caritas.Dto;



import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdottoResponseDto {
    private String id;
    private String nome;
    private String descrizione;
    private Set<UUID> categoriaIds;

}

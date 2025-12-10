package com.example.caritas.Dto;
import com.example.caritas.Entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdottoRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String nome;
    private String descrizione;
    private Set<UUID> categoriaIds;


}

package com.example.caritas.Mapper;

import com.example.caritas.Dto.GiacenzaRequestDto;
import com.example.caritas.Dto.GiacenzaResponseDto;
import com.example.caritas.Entity.Giacenza;
import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Entity.Prodotto;

public class GiacenzaMapper {

    public static GiacenzaResponseDto toDto(Giacenza giacenza) {
        GiacenzaResponseDto dto = new GiacenzaResponseDto();
        dto.setId(giacenza.getId().toString());
        dto.setQuantita(giacenza.getQuantita());

        if (giacenza.getProdotto() != null) {
            dto.setProdotto(ProdottoMapper.toDto(giacenza.getProdotto()));
        }
        return dto;
    }

    public static Giacenza toEntity(GiacenzaRequestDto dto, Magazzino magazzino, Prodotto prodotto) {
        Giacenza giacenza = new Giacenza();
        giacenza.setQuantita(dto.getQuantita());
        giacenza.setMagazzino(magazzino);
        giacenza.setProdotto(prodotto);
        return giacenza;
    }

}

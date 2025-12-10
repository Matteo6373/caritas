package com.example.caritas.Mapper;

import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Dto.ProdottoResponseDto;
import com.example.caritas.Entity.Categoria;
import com.example.caritas.Entity.Prodotto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


public class ProdottoMapper {

    public static ProdottoResponseDto toDto(Prodotto prodotto) {
        ProdottoResponseDto prodottoResponseDto = new ProdottoResponseDto();
        prodottoResponseDto.setId(prodotto.getId().toString());
        prodottoResponseDto.setNome(prodotto.getNome());
        prodottoResponseDto.setDescrizione(prodotto.getDescrizione());
        Set<Categoria> categorieCopiate = prodotto.getCategorie();
        Set<UUID> categoriaIds = categorieCopiate.stream()
                .map(Categoria::getId)
                .collect(Collectors.toSet());
        prodottoResponseDto.setCategoriaIds(categoriaIds);
        return prodottoResponseDto;
    }
    public static Prodotto toEntity(ProdottoRequestDto dto, Set<Categoria> categorie) {

        Prodotto prodotto = new Prodotto();
        prodotto.setNome(dto.getNome());
        prodotto.setDescrizione(dto.getDescrizione());
        prodotto.setCategorie(categorie);
        return prodotto;
    }
}

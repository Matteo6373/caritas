package com.example.caritas.Mapper;


import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Dto.ProdottoResponseDto;

import com.example.caritas.Entity.Categoria;
import com.example.caritas.Entity.Prodotto;




public class ProdottoMapper {

    public static ProdottoResponseDto toDto(Prodotto prodotto) {
        ProdottoResponseDto prodottoResponseDto = new ProdottoResponseDto();
        prodottoResponseDto.setId(prodotto.getId().toString());
        prodottoResponseDto.setNome(prodotto.getNome());
        prodottoResponseDto.setDescrizione(prodotto.getDescrizione());
        prodottoResponseDto.setCategoria(CategoriaMapper.toDto(prodotto.getCategoria()));
        return prodottoResponseDto;

    }
    public static Prodotto toEntity(ProdottoRequestDto prodottoRequestdto, Categoria categoria) {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(prodottoRequestdto.getNome());
        prodotto.setDescrizione(prodottoRequestdto.getDescrizione());
        prodotto.setCategoria(categoria);
        return prodotto;
    }
}

package com.example.caritas.Service;

import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Dto.ProdottoResponseDto;
import com.example.caritas.Entity.Prodotto;

import java.util.Set;
import java.util.UUID;

public interface ProdottoService {
    ProdottoResponseDto creaProdotto(ProdottoRequestDto prodotto);
    ProdottoResponseDto updateProdotto(ProdottoRequestDto prodotto,UUID prodottoId);
    ProdottoResponseDto deleteProdotto(UUID id);
    Set<ProdottoResponseDto> getProdotti();
    Prodotto getProdottoById(UUID prodottoId);
}

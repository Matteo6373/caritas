package com.example.caritas.Service;

import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Dto.ProdottoResponseDto;
import com.example.caritas.Entity.Prodotto;

import java.util.UUID;

public interface ProdottoService {
    ProdottoResponseDto creaProdotto(ProdottoRequestDto prodotto);
    Prodotto trovaProdotto(UUID prodottoId);
}

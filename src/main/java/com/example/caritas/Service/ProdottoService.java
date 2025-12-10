package com.example.caritas.Service;

import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Dto.ProdottoResponseDto;

import java.util.UUID;

public interface ProdottoService {
    ProdottoResponseDto creaProdotto(ProdottoRequestDto prodotto);
    ProdottoResponseDto trovaProdotto(UUID prodottoId);
}

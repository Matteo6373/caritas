package com.example.caritas.Service;

import com.example.caritas.Dto.GiacenzaRequestDto;
import com.example.caritas.Dto.GiacenzaResponseDto;

import java.util.UUID;

public interface GiacenzaService {
    public GiacenzaResponseDto addProduct(GiacenzaRequestDto requestDto, UUID magazzinoId);
}

package com.example.caritas.Service;

import com.example.caritas.Dto.BeneficiarioRequestDto;
import com.example.caritas.Dto.BeneficiarioResponseDto;

public interface BeneficiarioService {
    public BeneficiarioResponseDto createBeneficiario(BeneficiarioRequestDto dto);
}

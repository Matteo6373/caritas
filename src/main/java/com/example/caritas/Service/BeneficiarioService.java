package com.example.caritas.Service;

import com.example.caritas.Dto.BeneficiarioRequestDto;
import com.example.caritas.Dto.BeneficiarioResponseDto;
import com.example.caritas.Entity.Beneficiario;

import java.util.UUID;

public interface BeneficiarioService {
    public BeneficiarioResponseDto createBeneficiario(BeneficiarioRequestDto dto);
    public BeneficiarioResponseDto updateBeneficiario(BeneficiarioRequestDto dto, UUID uuid);
    public Beneficiario getBeneficiario(UUID uuid);
    public BeneficiarioResponseDto deleteBeneficiario(UUID uuid);

}

package com.example.caritas.Mapper;

import com.example.caritas.Dto.BeneficiarioRequestDto;
import com.example.caritas.Dto.BeneficiarioResponseDto;
import com.example.caritas.Entity.Beneficiario;
import com.example.caritas.Entity.Magazzino;

public class BeneficiarioMapper {
    public static BeneficiarioResponseDto toDto(Beneficiario beneficiario) {
        BeneficiarioResponseDto dto = new BeneficiarioResponseDto();
        dto.setId(beneficiario.getId().toString());
        dto.setNome(beneficiario.getNome());
        dto.setCognome(beneficiario.getCognome());
        dto.setDataNascimento(beneficiario.getDataNascita());
        return dto;
    }
    public static Beneficiario toEntity(BeneficiarioRequestDto dto, Magazzino magazzino) {
        Beneficiario  beneficiario = new Beneficiario();
        beneficiario.setNome(dto.getNome());
        beneficiario.setCognome(dto.getCognome());
        beneficiario.setDataNascita(dto.getDataNascita());
        beneficiario.setMagazzino(magazzino);
        return beneficiario;
    }
}

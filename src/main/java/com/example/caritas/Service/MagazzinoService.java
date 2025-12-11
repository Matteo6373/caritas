package com.example.caritas.Service;

import com.example.caritas.Dto.GiacenzaResponseDto;
import com.example.caritas.Dto.MagazzinoRequestDto;
import com.example.caritas.Dto.MagazzinoResponseDto;
import com.example.caritas.Entity.Magazzino;

import java.util.Set;
import java.util.UUID;

public interface MagazzinoService {
    public MagazzinoResponseDto createMagazzino(MagazzinoRequestDto magazzinoRequestDto);
    public Magazzino findByUuid(UUID uuid);
    public MagazzinoResponseDto findMagazzinoById(UUID uuid);
//    public Set<GiacenzaResponseDto> findAllGiacenzeByMagazzinoId(UUID magazzinoId);
}

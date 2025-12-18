package com.example.caritas.Mapper;

import com.example.caritas.Dto.GiacenzaResponseDto;
import com.example.caritas.Dto.MagazzinoRequestDto;
import com.example.caritas.Dto.MagazzinoResponseDto;
import com.example.caritas.Entity.Giacenza;
import com.example.caritas.Entity.Magazzino;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MagazzinoMapper {

    public static MagazzinoResponseDto toDto(Magazzino magazzino) {
        MagazzinoResponseDto dto = new MagazzinoResponseDto();
        dto.setNome(magazzino.getNome());
        dto.setId(magazzino.getId().toString());
        if(magazzino.getGiacenze() != null) {
            Set<GiacenzaResponseDto> giacenzeResponseDto = magazzino.getGiacenze()
                    .stream()
                    .map(GiacenzaMapper::toDto)
                    .collect(Collectors.toSet());
            dto.setGiacenze(giacenzeResponseDto);
        }else{
            dto.setGiacenze(new HashSet<>());
        }
        return dto;
    }

    public static Magazzino toEntity(MagazzinoRequestDto dto,Set<Giacenza> giacenze) {
        Magazzino magazzino = new Magazzino();
        magazzino.setNome(dto.getNome());
        magazzino.setGiacenze(giacenze);
        return magazzino;
    }
}

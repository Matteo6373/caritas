package com.example.caritas.Mapper;

import com.example.caritas.Dto.CategoriaRequestDto;
import com.example.caritas.Dto.CategoriaResponseDto;
import com.example.caritas.Entity.Categoria;


public class CategoriaMapper {

    public static CategoriaResponseDto toDto(Categoria categoria) {
        CategoriaResponseDto categoriaResponseDto = new CategoriaResponseDto();
        categoriaResponseDto.setDescrizione(categoria.getDescrizione());
        categoriaResponseDto.setNome(categoria.getNome());
        categoriaResponseDto.setId(categoria.getId().toString());
        return categoriaResponseDto;
    }
    public static Categoria toEntity(CategoriaRequestDto categoriaRequestDto) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaRequestDto.getNome());
        categoria.setDescrizione(categoriaRequestDto.getDescrizione());
        return categoria;
    }
}

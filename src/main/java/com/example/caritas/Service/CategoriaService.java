package com.example.caritas.Service;

import com.example.caritas.Dto.CategoriaRequestDto;
import com.example.caritas.Dto.CategoriaResponseDto;
import com.example.caritas.Entity.Categoria;

import java.util.Set;
import java.util.UUID;

public interface CategoriaService {
    CategoriaResponseDto creaCategoria(CategoriaRequestDto categoriaRequestDto);
    Set<CategoriaResponseDto> getCategorias();
    CategoriaResponseDto updateCategoria(CategoriaRequestDto categoriaRequestDto,UUID id);
    CategoriaResponseDto deleteCategoria(UUID id);
    Categoria getCategoriaById(UUID id);
}

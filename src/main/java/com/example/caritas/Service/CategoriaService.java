package com.example.caritas.Service;

import com.example.caritas.Dto.CategoriaRequestDto;
import com.example.caritas.Dto.CategoriaResponseDto;

public interface CategoriaService {
    CategoriaResponseDto creaCategoria(CategoriaRequestDto categoriaRequestDto);
}

package com.example.caritas.Service;

import com.example.caritas.Dto.CategoriaRequestDto;
import com.example.caritas.Dto.CategoriaResponseDto;
import com.example.caritas.Entity.Categoria;

public interface CategoriaService {
    CategoriaResponseDto creaCategoria(CategoriaRequestDto categoriaRequestDto);
}

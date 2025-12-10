package com.example.caritas.Service;

import com.example.caritas.Dto.CategoriaRequestDto;
import com.example.caritas.Dto.CategoriaResponseDto;
import com.example.caritas.Entity.Categoria;
import com.example.caritas.Mapper.CategoriaMapper;
import com.example.caritas.Repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriaServiceImp implements CategoriaService{

    CategoriaRepository categoriaRepository;

    @Override
    public CategoriaResponseDto creaCategoria(CategoriaRequestDto categoriaRequestDto) {
        Categoria categoria = CategoriaMapper.toEntity(categoriaRequestDto);
        Categoria categoriaReturned = categoriaRepository.save(categoria);
        return CategoriaMapper.toDto(categoriaReturned);
    }
}

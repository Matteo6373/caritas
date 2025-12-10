package com.example.caritas.Controller;

import com.example.caritas.Dto.CategoriaRequestDto;
import com.example.caritas.Dto.CategoriaResponseDto;
import com.example.caritas.Service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorie")
@AllArgsConstructor
public class CategoriaController {
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> createCategoria(
            @RequestBody CategoriaRequestDto categoriaRequestDto){
            CategoriaResponseDto categoriaResponseDto = categoriaService.creaCategoria(categoriaRequestDto);
            return ResponseEntity.ok().body(categoriaResponseDto);
    }
}

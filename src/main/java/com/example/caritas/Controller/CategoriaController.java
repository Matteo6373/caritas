package com.example.caritas.Controller;

import com.example.caritas.Dto.CategoriaRequestDto;
import com.example.caritas.Dto.CategoriaResponseDto;
import com.example.caritas.Entity.Categoria;
import com.example.caritas.Service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/categorie")
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")

public class CategoriaController {
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> createCategoria(
            @RequestBody CategoriaRequestDto categoriaRequestDto){
        CategoriaResponseDto categoriaResponseDto = categoriaService.creaCategoria(categoriaRequestDto);
        return ResponseEntity.ok().body(categoriaResponseDto);
    }
    @GetMapping
    public ResponseEntity<Set<CategoriaResponseDto>> trovaCategorias(){
        Set<CategoriaResponseDto> categorie =  categoriaService.getCategorias();
        return ResponseEntity.ok().body(categorie);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> deleteCategoria(@PathVariable UUID id){
        CategoriaResponseDto categoriaResponseDto = categoriaService.deleteCategoria(id);
        return ResponseEntity.ok().body(categoriaResponseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> updateCategoria(
            @PathVariable UUID id, @RequestBody CategoriaRequestDto categoriaRequestDto){
        CategoriaResponseDto categoriaResponseDto = categoriaService.updateCategoria(categoriaRequestDto, id);
        return ResponseEntity.ok().body(categoriaResponseDto);
    }

}

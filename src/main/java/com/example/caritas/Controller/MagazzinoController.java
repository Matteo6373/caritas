package com.example.caritas.Controller;

import com.example.caritas.Dto.GiacenzaRequestDto;
import com.example.caritas.Dto.GiacenzaResponseDto;
import com.example.caritas.Dto.MagazzinoRequestDto;
import com.example.caritas.Dto.MagazzinoResponseDto;
import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Entity.Prodotto;
import com.example.caritas.Service.GiacenzaService;
import com.example.caritas.Service.MagazzinoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/magazzino")
@AllArgsConstructor
public class MagazzinoController {
    private MagazzinoService magazzinoService;
    private GiacenzaService giacenzaService;

    @GetMapping("/{id}")
    public ResponseEntity<MagazzinoResponseDto> findById(@PathVariable UUID id) {
        MagazzinoResponseDto magazzinoResponseDto = magazzinoService.findMagazzinoById(id);
        return ResponseEntity.ok(magazzinoResponseDto);
    }
    @PostMapping
    public ResponseEntity<MagazzinoResponseDto> createMagazzino(@RequestBody MagazzinoRequestDto magazzinoRequestDto) {
        MagazzinoResponseDto magazzinoResponseDto = magazzinoService.createMagazzino(magazzinoRequestDto);
        return ResponseEntity.ok(magazzinoResponseDto);
    }
    @PostMapping("/{id}")
    public ResponseEntity<GiacenzaResponseDto> addGiacenza(
            @PathVariable UUID id, @RequestBody GiacenzaRequestDto giacenzaRequestDto) {
            GiacenzaResponseDto giacenzaResponseDto = giacenzaService.addProduct(giacenzaRequestDto,id);
            return ResponseEntity.ok(giacenzaResponseDto);
    }
}

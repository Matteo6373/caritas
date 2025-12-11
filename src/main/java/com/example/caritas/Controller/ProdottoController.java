package com.example.caritas.Controller;

import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Dto.ProdottoResponseDto;
import com.example.caritas.Entity.Prodotto;
import com.example.caritas.Mapper.ProdottoMapper;
import com.example.caritas.Service.ProdottoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/prodotti")
@AllArgsConstructor
public class ProdottoController {

    ProdottoService prodottoService;

    @PostMapping
    public ResponseEntity<ProdottoResponseDto> creareProdotto(@RequestBody ProdottoRequestDto prodottoRequestDto){
        ProdottoResponseDto prodottoResponseDto = prodottoService.creaProdotto(prodottoRequestDto);
        return ResponseEntity.ok(prodottoResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdottoResponseDto> getProdotto(@PathVariable UUID id){
        Prodotto prodotto = prodottoService.trovaProdotto(id);
        ProdottoResponseDto prodottoResponseDto = ProdottoMapper.toDto(prodotto);
        return ResponseEntity.ok(prodottoResponseDto);
    }
}

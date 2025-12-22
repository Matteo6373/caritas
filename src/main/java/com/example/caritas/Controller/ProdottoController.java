package com.example.caritas.Controller;

import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Dto.ProdottoResponseDto;
import com.example.caritas.Entity.Prodotto;
import com.example.caritas.Mapper.ProdottoMapper;
import com.example.caritas.Service.ProdottoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/prodotti")
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ProdottoController {

    ProdottoService prodottoService;

    @PostMapping
    public ResponseEntity<ProdottoResponseDto> creareProdotto(@RequestBody ProdottoRequestDto prodottoRequestDto){
        ProdottoResponseDto prodottoResponseDto = prodottoService.creaProdotto(prodottoRequestDto);
        return new ResponseEntity<>(prodottoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdottoResponseDto> getProdotto(@PathVariable UUID id){

        Prodotto prodotto = prodottoService.getProdottoById(id);
        ProdottoResponseDto prodottoResponseDto = ProdottoMapper.toDto(prodotto);
        return ResponseEntity.ok(prodottoResponseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdottoResponseDto> deleteProdotto(@PathVariable UUID id){
        ProdottoResponseDto prodottoResponseDto = prodottoService.deleteProdotto(id);
        return ResponseEntity.ok(prodottoResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdottoResponseDto> updateProdotto(
            @PathVariable UUID id, @RequestBody ProdottoRequestDto prodottoRequestDto){
        ProdottoResponseDto prodottoResponseDto = prodottoService.updateProdotto(prodottoRequestDto, id);
        return ResponseEntity.ok(prodottoResponseDto);
    }

    @GetMapping
    public ResponseEntity<Set<ProdottoResponseDto>> getProdotti(){
        Set<ProdottoResponseDto> prodotti =  prodottoService.getProdotti();
        return ResponseEntity.ok(prodotti);
    }
}

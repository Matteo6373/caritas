package com.example.caritas.Controller;

import com.example.caritas.Dto.*;
import com.example.caritas.Service.BeneficiarioService;
import com.example.caritas.Service.GiacenzaService;
import com.example.caritas.Service.MagazzinoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/magazzino")
@AllArgsConstructor
public class MagazzinoController {
    private MagazzinoService magazzinoService;
    private GiacenzaService giacenzaService;
    private BeneficiarioService beneficiarioService;

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
    @PostMapping("/{id}/giacenze")
    public ResponseEntity<GiacenzaResponseDto> addGiacenza(
            @PathVariable UUID id, @RequestBody GiacenzaRequestDto giacenzaRequestDto) {
        GiacenzaResponseDto giacenzaResponseDto = giacenzaService.addProduct(giacenzaRequestDto,id);
        return ResponseEntity.ok(giacenzaResponseDto);
    }
    @DeleteMapping("/{id}/giacenze")
    public ResponseEntity<GiacenzaResponseDto> removeGiacenza(
            @PathVariable UUID id,  @RequestBody GiacenzaRequestDto giacenzaRequestDto){
        GiacenzaResponseDto giacenzaResponseDto = giacenzaService.removeProduct(giacenzaRequestDto,id);
        return ResponseEntity.ok(giacenzaResponseDto);
    }
    @GetMapping("/{id}/beneficiari")
    public ResponseEntity<Set<BeneficiarioResponseDto>>  findBeneficiari(@PathVariable UUID id) {
        Set<BeneficiarioResponseDto> beneficiariResponseDto = magazzinoService.findAllBeneficiarios(id);
        return ResponseEntity.ok(beneficiariResponseDto);
    }
    @PostMapping("/{id}/beneficiari")
    public ResponseEntity<BeneficiarioResponseDto> createBeneficiario(
            @PathVariable UUID id,@RequestBody BeneficiarioRequestDto beneficiarioRequestDto) {
        BeneficiarioResponseDto beneficiarioResponseDto = beneficiarioService.createBeneficiario(beneficiarioRequestDto);
        return ResponseEntity.ok(beneficiarioResponseDto);
    }
}

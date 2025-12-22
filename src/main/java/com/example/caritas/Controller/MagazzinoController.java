package com.example.caritas.Controller;

import com.example.caritas.Dto.*;
import com.example.caritas.Entity.Beneficiario;
import com.example.caritas.Security.CostumUserDetails;
import com.example.caritas.Service.BeneficiarioService;
import com.example.caritas.Service.GiacenzaService;
import com.example.caritas.Service.MagazzinoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/magazzino")
@AllArgsConstructor
public class MagazzinoController {
    private MagazzinoService magazzinoService;
    private GiacenzaService giacenzaService;

    @PreAuthorize("@authz.canAccessMagazzino(authentication, #id)")
    @GetMapping("/{id}")
    public ResponseEntity<MagazzinoResponseDto> findById(@PathVariable UUID id) {
        MagazzinoResponseDto magazzinoResponseDto = magazzinoService.findMagazzinoById(id);
        return ResponseEntity.ok(magazzinoResponseDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MagazzinoResponseDto> createMagazzino(@RequestBody MagazzinoRequestDto magazzinoRequestDto) {
        MagazzinoResponseDto magazzinoResponseDto = magazzinoService.createMagazzino(magazzinoRequestDto);
        return ResponseEntity.ok(magazzinoResponseDto);
    }

    @PreAuthorize("@authz.canAccessMagazzino(authentication, #id)")
    @PostMapping("/{id}/giacenze")
    public ResponseEntity<GiacenzaResponseDto> addGiacenza(
            @PathVariable UUID id, @RequestBody GiacenzaRequestDto giacenzaRequestDto) {
        GiacenzaResponseDto giacenzaResponseDto = giacenzaService.addProduct(giacenzaRequestDto, id);
        return ResponseEntity.ok(giacenzaResponseDto);
    }

    @PreAuthorize("@authz.canAccessMagazzino(authentication, #id)")
    @DeleteMapping("/{id}/giacenze")
    public ResponseEntity<GiacenzaResponseDto> removeGiacenza(
            @PathVariable UUID id, @RequestBody GiacenzaRequestDto giacenzaRequestDto) {
        GiacenzaResponseDto giacenzaResponseDto = giacenzaService.removeProduct(giacenzaRequestDto, id);
        return ResponseEntity.ok(giacenzaResponseDto);
    }

    @PreAuthorize("@authz.canAccessMagazzino(authentication, #id)")
    @GetMapping("/{id}/beneficiari")
    public ResponseEntity<Set<BeneficiarioResponseDto>> findBeneficiari(@PathVariable UUID id) {
        Set<BeneficiarioResponseDto> beneficiariResponseDto = magazzinoService.findAllBeneficiarios(id);
        return ResponseEntity.ok(beneficiariResponseDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MagazzinoResponseDto> updateMagazzino(
            @PathVariable UUID id, @RequestBody MagazzinoRequestDto magazzinoRequestDto) {
        MagazzinoResponseDto magazzinoResponseDto = magazzinoService.updateMagazzino(magazzinoRequestDto, id);
        return ResponseEntity.ok(magazzinoResponseDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MagazzinoResponseDto> removeMagazzino(@PathVariable UUID id) {
        MagazzinoResponseDto magazzinoResponseDto = magazzinoService.deleteMagazzino(id);
        return ResponseEntity.ok(magazzinoResponseDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Set<MagazzinoResponseDto>> findAllMagazzino() {
        Set<MagazzinoResponseDto> magazzinoResponseDtos = magazzinoService.findAllMagazzini();
        return ResponseEntity.ok(magazzinoResponseDtos);
    }
}

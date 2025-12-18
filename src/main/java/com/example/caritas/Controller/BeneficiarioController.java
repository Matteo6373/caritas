package com.example.caritas.Controller;

import com.example.caritas.Dto.BeneficiarioRequestDto;
import com.example.caritas.Dto.BeneficiarioResponseDto;
import com.example.caritas.Service.BeneficiarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/beneficiari")
@AllArgsConstructor
@PreAuthorize("authentication.principal.role == 'ROLE_ADMIN'")
public class BeneficiarioController {

    BeneficiarioService beneficiarioService;

    @PostMapping("")
    public ResponseEntity<BeneficiarioResponseDto> createBeneficiario(
            @RequestBody BeneficiarioRequestDto beneficiarioRequestDto) {
        BeneficiarioResponseDto beneficiarioResponseDto = beneficiarioService.createBeneficiario(beneficiarioRequestDto);
        return ResponseEntity.ok(beneficiarioResponseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BeneficiarioResponseDto> removeBeneficiario(
            @PathVariable UUID id){
        BeneficiarioResponseDto beneficiarioResponseDto = beneficiarioService.deleteBeneficiario(id);
        return ResponseEntity.ok(beneficiarioResponseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BeneficiarioResponseDto> updateBeneficiario(
            @PathVariable UUID id,@RequestBody BeneficiarioRequestDto beneficiarioRequestDto) {
        BeneficiarioResponseDto beneficiarioResponseDto = beneficiarioService.updateBeneficiario(beneficiarioRequestDto, id);
        return ResponseEntity.ok(beneficiarioResponseDto);
    }
}

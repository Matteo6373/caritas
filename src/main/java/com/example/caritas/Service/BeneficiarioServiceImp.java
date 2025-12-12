package com.example.caritas.Service;

import com.example.caritas.Dto.BeneficiarioRequestDto;
import com.example.caritas.Dto.BeneficiarioResponseDto;
import com.example.caritas.Entity.Beneficiario;
import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Mapper.BeneficiarioMapper;
import com.example.caritas.Repository.BeneficiarioRepository;
import com.example.caritas.exception.AlreadyExistsByNomeException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@AllArgsConstructor
public class BeneficiarioServiceImp implements BeneficiarioService {
    private BeneficiarioRepository beneficiarioRepository;
    private MagazzinoService magazzinoService;

    public BeneficiarioResponseDto createBeneficiario(BeneficiarioRequestDto dto){
        if(beneficiarioRepository.existsByNome(dto.getNome())){
            throw new AlreadyExistsByNomeException(
                    "A Beneficiario with this nome already exists:"
                            + dto.getNome());
        }
        Magazzino magazzino = magazzinoService.findByUuid(dto.getMagazzinoId());
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setMagazzino(magazzino);
        beneficiario.setNome(dto.getNome());
        beneficiario.setCognome(dto.getCognome());
        beneficiario.setDataNascita(dto.getDataNascita());
        Beneficiario beneficiarioReturned = beneficiarioRepository.save(beneficiario);
        return BeneficiarioMapper.toDto(beneficiarioReturned);
    }
}

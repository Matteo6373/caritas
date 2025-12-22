package com.example.caritas.Service;

import com.example.caritas.Dto.BeneficiarioRequestDto;
import com.example.caritas.Dto.BeneficiarioResponseDto;
import com.example.caritas.Dto.MagazzinoRequestDto;
import com.example.caritas.Dto.MagazzinoResponseDto;
import com.example.caritas.Entity.Beneficiario;
import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Mapper.BeneficiarioMapper;
import com.example.caritas.Mapper.MagazzinoMapper;
import com.example.caritas.Repository.BeneficiarioRepository;
import com.example.caritas.exception.AlreadyExistsByNomeException;
import com.example.caritas.exception.NullException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class BeneficiarioServiceImp implements BeneficiarioService {
    private BeneficiarioRepository beneficiarioRepository;
    private MagazzinoService magazzinoService;

    public BeneficiarioResponseDto createBeneficiario(BeneficiarioRequestDto dto){
        if(beneficiarioRepository.existsByNome(dto.getNome())){
            throw new AlreadyExistsByNomeException(
                    "A Beneficiario with this nome already exists:"
                            + dto.getNome());
        }
        Magazzino magazzino = magazzinoService.getMagazzino(dto.getMagazzinoId());
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setMagazzino(magazzino);
        beneficiario.setNome(dto.getNome());
        beneficiario.setCognome(dto.getCognome());
        beneficiario.setDataNascita(dto.getDataNascita());
        Beneficiario beneficiarioReturned = beneficiarioRepository.save(beneficiario);
        return BeneficiarioMapper.toDto(beneficiarioReturned);
    }

    @Override
    public BeneficiarioResponseDto updateBeneficiario(BeneficiarioRequestDto dto, UUID uuid) {
        if(beneficiarioRepository.existsByNome(dto.getNome())){
            throw new AlreadyExistsByNomeException(
                    "A Beneficiario with this nome already exists:"+ dto.getNome()
            );
        }
        Beneficiario beneficiario = getBeneficiario(uuid);
        Magazzino magazzino = magazzinoService.getMagazzino(dto.getMagazzinoId());
        beneficiario.setMagazzino(magazzino);
        beneficiario.setCognome(dto.getCognome());
        beneficiario.setNome(dto.getNome());
        beneficiario.setDataNascita(dto.getDataNascita());
        Beneficiario beneficiarioReturned = beneficiarioRepository.save(beneficiario);
        return BeneficiarioMapper.toDto(beneficiarioReturned);
    }

    @Override
    public Beneficiario getBeneficiario(UUID uuid) {
        if(uuid == null) {
            throw new NullException("id of Beneficiaio is null");
        }
        return beneficiarioRepository.findById(uuid).orElseThrow(()
                -> new NullException("Beneficiario non trovato"));
    }

    @Override
    public BeneficiarioResponseDto deleteBeneficiario(UUID uuid) {
        Beneficiario beneficiario = getBeneficiario(uuid);
        beneficiarioRepository.delete(beneficiario);
        return BeneficiarioMapper.toDto(beneficiario);
    }

}

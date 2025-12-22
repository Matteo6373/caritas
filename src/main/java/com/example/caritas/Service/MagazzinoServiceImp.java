package com.example.caritas.Service;

import com.example.caritas.Dto.BeneficiarioResponseDto;
import com.example.caritas.Dto.MagazzinoRequestDto;
import com.example.caritas.Dto.MagazzinoResponseDto;
import com.example.caritas.Entity.Beneficiario;
import com.example.caritas.Entity.Categoria;
import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Entity.Prodotto;
import com.example.caritas.Mapper.BeneficiarioMapper;
import com.example.caritas.Mapper.MagazzinoMapper;
import com.example.caritas.Mapper.ProdottoMapper;
import com.example.caritas.Repository.BeneficiarioRepository;
import com.example.caritas.Repository.MagazzinoRepository;
import com.example.caritas.exception.AlreadyExistsByNomeException;
import com.example.caritas.exception.NullException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Transactional
public class MagazzinoServiceImp implements MagazzinoService{
    private MagazzinoRepository magazzinoRepository;

    public MagazzinoResponseDto createMagazzino(MagazzinoRequestDto magazzinoRequestDto){
        Magazzino magazzino = MagazzinoMapper.toEntity(magazzinoRequestDto,null);
        if (magazzinoRepository.existsByNome(magazzinoRequestDto.getNome())) {
            throw new AlreadyExistsByNomeException(
                    "A Magazine with this nome already exists:"
                            + magazzinoRequestDto.getNome());
        }
        Magazzino magazzinoReturned = magazzinoRepository.save(magazzino);
        return MagazzinoMapper.toDto(magazzinoReturned);
    }

    @Override
    public Magazzino getMagazzino(UUID uuid) {
        if(uuid == null) {
            throw new NullException("id of Magazzino is null");
        }
        return magazzinoRepository.findById(uuid).orElseThrow(()
                ->new NullException("Magazzino not found"));

    }

    @Override
    public MagazzinoResponseDto findMagazzinoById(UUID uuid) {
        Magazzino magazzino = getMagazzino(uuid);
        return MagazzinoMapper.toDto(magazzino);
    }


    public Set<BeneficiarioResponseDto> findAllBeneficiarios(UUID magazzinoId){
        Magazzino magazzino = getMagazzino(magazzinoId);
        return magazzino.getBeneficiari()
                .stream().map(BeneficiarioMapper::toDto)
                .collect(Collectors.toSet());

    }

    @Override
    public MagazzinoResponseDto updateMagazzino(MagazzinoRequestDto magazzinoRequestDto, UUID uuid) {
        if(magazzinoRepository.existsByNome(magazzinoRequestDto.getNome())){
            throw new AlreadyExistsByNomeException(
                    "A Magazine with this nome already exists:"+ magazzinoRequestDto.getNome()
            );
        }
        Magazzino magazzino = getMagazzino(uuid);
        magazzino.setNome(magazzinoRequestDto.getNome());
        Magazzino magazzinoUpdated = magazzinoRepository.save(magazzino);
        return MagazzinoMapper.toDto(magazzinoUpdated);
    }

    @Override
    @Transactional
    public MagazzinoResponseDto deleteMagazzino(UUID uuid) {
        Magazzino magazzino = getMagazzino(uuid);
        magazzinoRepository.delete(magazzino);
        return MagazzinoMapper.toDto(magazzino);
    }

    @Override
    public Set<MagazzinoResponseDto> findAllMagazzini() {
        List<Magazzino> magazzini = magazzinoRepository.findAll();

        if (magazzini == null) {
            return Collections.emptySet();
        }

        return magazzini.stream()
                .map(MagazzinoMapper::toDto)
                .collect(Collectors.toSet());
    }



    //    @Override
//    public Set<GiacenzaResponseDto> findAllGiacenzeByMagazzinoId(UUID magazzinoId) {
//        Magazzino magazzino = magazzinoRepository.findById(magazzinoId)
//                .orElseThrow(() -> new NullException("Magazzino non trovato"));
//
//        return magazzino.getGiacenze()
//                .stream()
//                .map(GiacenzaMapper::toDto)
//                .collect(Collectors.toSet());
//    }

}

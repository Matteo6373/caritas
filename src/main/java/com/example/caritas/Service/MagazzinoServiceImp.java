package com.example.caritas.Service;

import com.example.caritas.Dto.GiacenzaResponseDto;
import com.example.caritas.Dto.MagazzinoRequestDto;
import com.example.caritas.Dto.MagazzinoResponseDto;
import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Mapper.GiacenzaMapper;
import com.example.caritas.Mapper.MagazzinoMapper;
import com.example.caritas.Repository.MagazzinoRepository;
import com.example.caritas.exception.AlreadyExistsByNomeException;
import com.example.caritas.exception.NullException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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
    public Magazzino findByUuid(UUID uuid) {
        Magazzino magazzino = magazzinoRepository.findById(uuid).get();
        return magazzino;
    }

    @Override
    public MagazzinoResponseDto findMagazzinoById(UUID uuid) {
        Magazzino magazzino = findByUuid(uuid);
        return MagazzinoMapper.toDto(magazzino);
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

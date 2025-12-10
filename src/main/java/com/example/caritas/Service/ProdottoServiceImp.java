package com.example.caritas.Service;

import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Dto.ProdottoResponseDto;
import com.example.caritas.Entity.Categoria;
import com.example.caritas.Entity.Prodotto;
import com.example.caritas.Mapper.ProdottoMapper;
import com.example.caritas.Repository.CategoriaRepository;
import com.example.caritas.Repository.ProdottoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProdottoServiceImp implements ProdottoService {

    ProdottoRepository prodottoRepository;
    CategoriaRepository categoriaRepository;

    @Override
    public ProdottoResponseDto creaProdotto(ProdottoRequestDto prodottoRequestDto) {
        Set<Categoria> categorie = convertCatogorie(prodottoRequestDto.getCategoriaIds());
        Prodotto prodotto = ProdottoMapper.toEntity(prodottoRequestDto,categorie);
        Prodotto prodottoReturned = prodottoRepository.save(prodotto);
        return ProdottoMapper.toDto(prodottoReturned);
    }

    @Override
    public ProdottoResponseDto trovaProdotto(UUID prodottoId) {
        System.out.println(prodottoId);
        Prodotto prodotto = prodottoRepository.findById(prodottoId).orElse(null);
        return ProdottoMapper.toDto(prodotto);
    }

    private Set<Categoria> convertCatogorie(Set<UUID> categorieUuids){
        Set<Categoria> categorie = new HashSet<>();
        if (categorieUuids != null) {
            for (UUID categoriaId : categorieUuids) {
                Categoria categoria = categoriaRepository.findById(categoriaId)
                        .orElseThrow(() -> new RuntimeException("Categoria non trovata: " + categoriaId));
                categorie.add(categoria);
            }
        }
        return categorie;
    }
}

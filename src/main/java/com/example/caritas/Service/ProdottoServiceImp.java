package com.example.caritas.Service;

import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Dto.ProdottoResponseDto;
import com.example.caritas.Entity.Categoria;
import com.example.caritas.Entity.Prodotto;
import com.example.caritas.Mapper.ProdottoMapper;
import com.example.caritas.Repository.CategoriaRepository;
import com.example.caritas.Repository.ProdottoRepository;
import com.example.caritas.exception.AlreadyExistsByNomeException;
import com.example.caritas.exception.NullException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProdottoServiceImp implements ProdottoService {

    ProdottoRepository prodottoRepository;
    CategoriaRepository categoriaRepository;

    @Override
    public ProdottoResponseDto creaProdotto(ProdottoRequestDto prodottoRequestDto) {
        Categoria categoria = null;
        if (prodottoRequestDto.getCategoriaId() != null) {
            Optional<Categoria> c = categoriaRepository.findById(prodottoRequestDto.getCategoriaId());
            if (c.isPresent()) {
                categoria = c.get();
            }
        }
        Prodotto prodotto = ProdottoMapper.toEntity(prodottoRequestDto, categoria);
        if(prodottoRepository.existsByNome(prodotto.getNome())){
            throw new AlreadyExistsByNomeException(
                    "A Product with this nome already exists:"
                            + prodottoRequestDto.getNome());
        }
        Prodotto prodottoReturned = prodottoRepository.save(prodotto);
        return ProdottoMapper.toDto(prodottoReturned);
    }

    @Override
    public Prodotto trovaProdotto(UUID prodottoId) {
        Prodotto prodotto = prodottoRepository.findById(prodottoId)
                .orElseThrow(()-> new NullException("Prodotto non trovato"));
        return prodotto;
    }
}

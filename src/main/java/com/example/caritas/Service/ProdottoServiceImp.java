package com.example.caritas.Service;

import com.example.caritas.Dto.CategoriaResponseDto;
import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Dto.ProdottoResponseDto;
import com.example.caritas.Entity.Categoria;
import com.example.caritas.Entity.Prodotto;
import com.example.caritas.Mapper.CategoriaMapper;
import com.example.caritas.Mapper.ProdottoMapper;
import com.example.caritas.Repository.CategoriaRepository;
import com.example.caritas.Repository.ProdottoRepository;
import com.example.caritas.exception.AlreadyExistsByNomeException;
import com.example.caritas.exception.DeleteException;
import com.example.caritas.exception.NullException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class ProdottoServiceImp implements ProdottoService {

    ProdottoRepository prodottoRepository;
    CategoriaService categoriaService;

    @Override
    public ProdottoResponseDto creaProdotto(ProdottoRequestDto prodottoRequestDto) {
        Categoria categoria = categoriaService.getCategoriaById(prodottoRequestDto.getCategoriaId());
        Prodotto prodotto = ProdottoMapper.toEntity(prodottoRequestDto, categoria);
        if(prodottoRepository.existsByNome(prodotto.getNome())){
            throw new AlreadyExistsByNomeException(
                    "A Product with this nome already exists:"
                            + prodottoRequestDto.getNome());
        }
        categoria.addProdotto(prodotto);
        Prodotto prodottoReturned = prodottoRepository.save(prodotto);
        return ProdottoMapper.toDto(prodottoReturned);
    }

    @Override
    public ProdottoResponseDto updateProdotto(ProdottoRequestDto requestDto, UUID prodottoId) {
        if(prodottoRepository.existsByNome(requestDto.getNome())){
            throw new AlreadyExistsByNomeException(
                    "A Product with this nome already exists:"+ requestDto.getNome()
            );
        }
        Prodotto prodotto = getProdottoById(prodottoId);
        Categoria categoria = categoriaService.getCategoriaById(requestDto.getCategoriaId());
        prodotto.setCategoria(categoria);
        prodotto.setDescrizione(requestDto.getDescrizione());
        prodotto.setNome(requestDto.getNome());
        Prodotto prodottoReturned = prodottoRepository.save(prodotto);
        return ProdottoMapper.toDto(prodottoReturned);
    }

    @Override
    public ProdottoResponseDto deleteProdotto(UUID id) {
        Prodotto prodotto = getProdottoById(id);
        try {
            prodottoRepository.delete(prodotto);
            return ProdottoMapper.toDto(prodotto);
        }catch (DataIntegrityViolationException e){
            throw new DeleteException(
                    "can't delete prodotto with id:"+id + " because exists in a magazine"
            );
        }
    }

    @Override
    public Set<ProdottoResponseDto> getProdotti() {
        Set<Prodotto> prodotti = new HashSet<>(prodottoRepository.findAll());
        Set<ProdottoResponseDto> prodottiResponseDto = new HashSet<>();
        for (Prodotto prodotto : prodotti) {
            prodottiResponseDto.add(ProdottoMapper.toDto(prodotto));
        }
        return prodottiResponseDto;

    }

    @Override
    public Prodotto getProdottoById(UUID id) {
        if(id == null) {
            throw new NullException("id of prodotto is null");
        }
        return prodottoRepository.findById(id)
                .orElseThrow(()-> new NullException("Prodotto non trovato"));

    }
}

package com.example.caritas.Service;

import com.example.caritas.Dto.CategoriaRequestDto;
import com.example.caritas.Dto.CategoriaResponseDto;
import com.example.caritas.Entity.Categoria;
import com.example.caritas.Mapper.CategoriaMapper;
import com.example.caritas.Repository.CategoriaRepository;
import com.example.caritas.exception.AlreadyExistsByNomeException;
import com.example.caritas.exception.DeleteException;
import com.example.caritas.exception.NullException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoriaServiceImp implements CategoriaService{

    CategoriaRepository categoriaRepository;

    @Override
    public CategoriaResponseDto creaCategoria(CategoriaRequestDto categoriaRequestDto) {
        Categoria categoria = CategoriaMapper.toEntity(categoriaRequestDto);
        if (categoriaRepository.existsByNome(categoriaRequestDto.getNome())) {
            throw new AlreadyExistsByNomeException(
                    "A Category with this nome already exists:"
                            + categoriaRequestDto.getNome());
        }
        Categoria categoriaReturned = categoriaRepository.save(categoria);
        return CategoriaMapper.toDto(categoriaReturned);
    }

    @Override
    public Set<CategoriaResponseDto> getCategorias() {
        Set<Categoria> categorie = new HashSet<>(categoriaRepository.findAll());
        Set<CategoriaResponseDto> categoriaResponseDto = new HashSet<>();
        for (Categoria categoria : categorie) {
            categoriaResponseDto.add(CategoriaMapper.toDto(categoria));
        }
        return categoriaResponseDto;
    }

    @Override
    public CategoriaResponseDto updateCategoria(CategoriaRequestDto categoriaRequestDto, UUID id) {
        Categoria categoria = getCategoriaById(id);
        if(categoriaRepository.existsByNome(categoriaRequestDto.getNome())) {
            throw new AlreadyExistsByNomeException(
                    "A Category with this nome already exists:"+categoriaRequestDto.getNome()
            );
        }
        categoria.setNome(categoriaRequestDto.getNome());
        categoria.setDescrizione(categoriaRequestDto.getDescrizione());
        return CategoriaMapper.toDto(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaResponseDto deleteCategoria(UUID id) {
        Categoria categoria = getCategoriaById(id);
        if (categoria.getProdotti() != null && !categoria.getProdotti().isEmpty()) {
            throw new DeleteException(
                    "Can't delete categoria with id: " + id + " because it is referenced by a product"
            );
        }
        categoriaRepository.delete(categoria);
        return CategoriaMapper.toDto(categoria);
    }


    public Categoria getCategoriaById(UUID id) {
        if(id == null) {
            throw new NullException("id of category is null");
        }
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()-> new NullException("Categoria non trovata"));
        return categoria;
    }
}

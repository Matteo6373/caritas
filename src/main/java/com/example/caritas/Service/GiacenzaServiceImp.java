package com.example.caritas.Service;

import com.example.caritas.Dto.GiacenzaRequestDto;
import com.example.caritas.Dto.GiacenzaResponseDto;
import com.example.caritas.Entity.Giacenza;
import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Entity.Prodotto;
import com.example.caritas.Mapper.GiacenzaMapper;
import com.example.caritas.Repository.GiacenzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GiacenzaServiceImp implements GiacenzaService{
    private GiacenzaRepository giacenzaRepository;
    private MagazzinoService magazzinoService;
    private ProdottoService prodottoService;
    @Override
    public GiacenzaResponseDto addProduct(GiacenzaRequestDto requestDto, UUID magazzinoId) {
        Magazzino magazzino = magazzinoService.findByUuid(magazzinoId);
        Prodotto prodotto = prodottoService.trovaProdotto(requestDto.getProdottoId());
        Set<Giacenza> giacenze = magazzino.getGiacenze();
        for (Giacenza giacenza : giacenze) {
            if (giacenza.getProdotto().getId().equals(prodotto.getId())) {
                // aggiorna quantità
                giacenza.setQuantita(
                        (giacenza.getQuantita() != null ? giacenza.getQuantita() : 0)
                                + (requestDto.getQuantita() != null ? requestDto.getQuantita() : 0)
                );
                Giacenza giacenzaSaved = giacenzaRepository.save(giacenza);
                return GiacenzaMapper.toDto(giacenzaSaved);
            }
        }

        // se non esiste ancora, crea nuova giacenza
        Giacenza giacenzaUpdated = new Giacenza();
        giacenzaUpdated.setProdotto(prodotto);
        giacenzaUpdated.setMagazzino(magazzino);
        giacenzaUpdated.setQuantita(requestDto.getQuantita());

        Giacenza giacenzaSaved = giacenzaRepository.save(giacenzaUpdated);
        return GiacenzaMapper.toDto(giacenzaSaved);
    }

}

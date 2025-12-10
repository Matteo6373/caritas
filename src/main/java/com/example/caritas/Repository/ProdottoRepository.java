package com.example.caritas.Repository;

import com.example.caritas.Entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, UUID> {
    boolean existsByNome(String nome);
}

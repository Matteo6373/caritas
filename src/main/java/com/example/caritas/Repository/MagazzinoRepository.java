package com.example.caritas.Repository;

import com.example.caritas.Entity.Magazzino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface MagazzinoRepository extends JpaRepository<Magazzino, UUID> {
    boolean existsByNome(String nome);
}

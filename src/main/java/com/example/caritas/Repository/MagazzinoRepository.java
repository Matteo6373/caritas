package com.example.caritas.Repository;

import com.example.caritas.Entity.Magazzino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MagazzinoRepository extends JpaRepository<Magazzino, UUID> {
}

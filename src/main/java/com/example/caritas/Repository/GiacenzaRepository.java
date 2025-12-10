package com.example.caritas.Repository;

import com.example.caritas.Entity.Giacenza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GiacenzaRepository extends JpaRepository<Giacenza, UUID> {
}

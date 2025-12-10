package com.example.caritas.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "magazzino")
@Getter
@Setter
public class Magazzino {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    @OneToMany(mappedBy = "magazzino")
    private Set<Giacenza> giacenze = new HashSet<>();


}

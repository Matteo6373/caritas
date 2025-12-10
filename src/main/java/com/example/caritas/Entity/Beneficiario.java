package com.example.caritas.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "beneficiario")
@Getter
@Setter
public class Beneficiario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String nome;
    @NotNull
    private String cognome;
    @NotNull
    private LocalDate dataNascita;

}

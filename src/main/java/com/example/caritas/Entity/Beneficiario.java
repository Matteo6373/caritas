package com.example.caritas.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "beneficiario")
@AllArgsConstructor
@NoArgsConstructor
@Data
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

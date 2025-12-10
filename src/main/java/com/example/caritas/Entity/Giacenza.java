package com.example.caritas.Entity;

import com.example.caritas.Entity.Magazzino;
import com.example.caritas.Entity.Prodotto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@Entity
@Table(name = "giacenza")
@Data
@NoArgsConstructor
public class Giacenza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "magazzino_id")
    private Magazzino magazzino;

    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private Prodotto prodotto;

    @NotNull
    private Integer quantita;
}

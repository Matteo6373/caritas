package com.example.caritas.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "prodotti")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String nome;

    private String descrizione;

    @ManyToMany
    @JoinTable(
            name = "prodotto_categoria",
            joinColumns = @JoinColumn(name = "prodotto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorie = new HashSet<>();

    @OneToMany(mappedBy = "prodotto")
    private Set<Giacenza> giacenze = new HashSet<>();

}

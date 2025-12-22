package com.example.caritas.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "categoria")
@Getter
@Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank
    private String nome;
    private String descrizione;
    @OneToMany(mappedBy = "categoria")
    private Set<Prodotto> prodotti = new HashSet<>();

    public void addProdotto(Prodotto prodotto) {
        prodotti.add(prodotto);
        prodotto.setCategoria(this);
    }

    public void removeProdotto(Prodotto prodotto) {
        prodotti.remove(prodotto);
        prodotto.setCategoria(null);
    }
}

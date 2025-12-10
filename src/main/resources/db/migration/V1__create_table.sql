CREATE TABLE beneficiario
(
    id           UUID         NOT NULL,
    nome         VARCHAR(255) NOT NULL,
    cognome      VARCHAR(255) NOT NULL,
    data_nascita date         NOT NULL,
    CONSTRAINT pk_beneficiario PRIMARY KEY (id)
);

CREATE TABLE categoria
(
    id          UUID         NOT NULL,
    nome        VARCHAR(255) NOT NULL,
    descrizione VARCHAR(255),
    CONSTRAINT pk_categoria PRIMARY KEY (id)
);

CREATE TABLE giacenza
(
    id           UUID    NOT NULL,
    magazzino_id UUID,
    prodotto_id  UUID,
    quantita     INTEGER NOT NULL,
    CONSTRAINT pk_giacenza PRIMARY KEY (id)
);

CREATE TABLE magazzino
(
    id   UUID NOT NULL,
    nome VARCHAR(255),
    CONSTRAINT pk_magazzino PRIMARY KEY (id)
);

CREATE TABLE prodotti
(
    id           UUID         NOT NULL,
    nome         VARCHAR(255) NOT NULL,
    descrizione  VARCHAR(255),
    categoria_id UUID,
    CONSTRAINT pk_prodotti PRIMARY KEY (id)
);

ALTER TABLE giacenza
    ADD CONSTRAINT FK_GIACENZA_ON_MAGAZZINO FOREIGN KEY (magazzino_id) REFERENCES magazzino (id);

ALTER TABLE giacenza
    ADD CONSTRAINT FK_GIACENZA_ON_PRODOTTO FOREIGN KEY (prodotto_id) REFERENCES prodotti (id);

ALTER TABLE prodotti
    ADD CONSTRAINT FK_PRODOTTI_ON_CATEGORIA FOREIGN KEY (categoria_id) REFERENCES categoria (id);
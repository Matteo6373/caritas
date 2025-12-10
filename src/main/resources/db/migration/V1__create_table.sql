CREATE TABLE beneficiario
(
    id           UUID NOT NULL,
    nome         VARCHAR(255),
    cognome      VARCHAR(255),
    data_nascita date,
    CONSTRAINT pk_beneficiario PRIMARY KEY (id)
);

CREATE TABLE categoria
(
    id          UUID NOT NULL,
    nome        VARCHAR(255),
    descrizione VARCHAR(255),
    CONSTRAINT pk_categoria PRIMARY KEY (id)
);

CREATE TABLE giacenza
(
    id           UUID NOT NULL,
    magazzino_id UUID,
    prodotto_id  UUID,
    quantita     INTEGER,
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
    id          UUID NOT NULL,
    nome        VARCHAR(255),
    descrizione VARCHAR(255),
    CONSTRAINT pk_prodotti PRIMARY KEY (id)
);

CREATE TABLE prodotto_categoria
(
    categoria_id UUID NOT NULL,
    prodotto_id  UUID NOT NULL,
    CONSTRAINT pk_prodotto_categoria PRIMARY KEY (categoria_id, prodotto_id)
);

ALTER TABLE giacenza
    ADD CONSTRAINT FK_GIACENZA_ON_MAGAZZINO FOREIGN KEY (magazzino_id) REFERENCES magazzino (id);

ALTER TABLE giacenza
    ADD CONSTRAINT FK_GIACENZA_ON_PRODOTTO FOREIGN KEY (prodotto_id) REFERENCES prodotti (id);

ALTER TABLE prodotto_categoria
    ADD CONSTRAINT fk_procat_on_categoria FOREIGN KEY (categoria_id) REFERENCES categoria (id);

ALTER TABLE prodotto_categoria
    ADD CONSTRAINT fk_procat_on_prodotto FOREIGN KEY (prodotto_id) REFERENCES prodotti (id);
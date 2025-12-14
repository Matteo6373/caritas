CREATE TABLE beneficiario
(
    id           UUID         NOT NULL,
    nome         VARCHAR(255) NOT NULL,
    cognome      VARCHAR(255) NOT NULL,
    data_nascita date         NOT NULL,
    magazzino_id UUID         NOT NULL,
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
    id           UUID    NOT NULL,
    magazzino_id UUID    NOT NULL,
    prodotto_id  UUID    NOT NULL,
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

CREATE TABLE user_magazzino
(
    magazzino_id UUID NOT NULL,
    user_id      UUID NOT NULL,
    CONSTRAINT pk_user_magazzino PRIMARY KEY (magazzino_id, user_id)
);

CREATE TABLE users
(
    id       UUID NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    role     VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE beneficiario
    ADD CONSTRAINT FK_BENEFICIARIO_ON_MAGAZZINO FOREIGN KEY (magazzino_id) REFERENCES magazzino (id);

ALTER TABLE giacenza
    ADD CONSTRAINT FK_GIACENZA_ON_MAGAZZINO FOREIGN KEY (magazzino_id) REFERENCES magazzino (id);

ALTER TABLE giacenza
    ADD CONSTRAINT FK_GIACENZA_ON_PRODOTTO FOREIGN KEY (prodotto_id) REFERENCES prodotti (id);

ALTER TABLE prodotti
    ADD CONSTRAINT FK_PRODOTTI_ON_CATEGORIA FOREIGN KEY (categoria_id) REFERENCES categoria (id);

ALTER TABLE user_magazzino
    ADD CONSTRAINT fk_usemag_on_magazzino FOREIGN KEY (magazzino_id) REFERENCES magazzino (id);

ALTER TABLE user_magazzino
    ADD CONSTRAINT fk_usemag_on_user FOREIGN KEY (user_id) REFERENCES users (id);
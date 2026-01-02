-- Inserimento Magazzini
INSERT INTO magazzino (id, nome) VALUES
                                     (gen_random_uuid(), 'Magazzino Centrale'),
                                     (gen_random_uuid(), 'Magazzino Sud');

-- Inserimento Categorie
INSERT INTO categoria (id, nome, descrizione) VALUES
                                                  (gen_random_uuid(), 'Alimentari', 'Cibi e bevande'),
                                                  (gen_random_uuid(), 'Vestiti', 'Abbigliamento e accessori');

-- Inserimento Prodotti
INSERT INTO prodotti (id, nome, descrizione, categoria_id)
SELECT gen_random_uuid(), 'Pasta', 'Pasta di grano duro', id FROM categoria WHERE nome='Alimentari';

INSERT INTO prodotti (id, nome, descrizione, categoria_id)
SELECT gen_random_uuid(), 'Maglietta', 'Maglietta cotone', id FROM categoria WHERE nome='Vestiti';

-- Inserimento Beneficiari
INSERT INTO beneficiario (id, nome, cognome, data_nascita, magazzino_id)
SELECT gen_random_uuid(), 'Mario', 'Rossi', '1980-05-12', id FROM magazzino WHERE nome='Magazzino Centrale';

INSERT INTO beneficiario (id, nome, cognome, data_nascita, magazzino_id)
SELECT gen_random_uuid(), 'Lucia', 'Bianchi', '1990-08-23', id FROM magazzino WHERE nome='Magazzino Sud';

-- Inserimento Giacenze
INSERT INTO giacenza (id, magazzino_id, prodotto_id, quantita)
SELECT gen_random_uuid(), m.id, p.id, 100
FROM magazzino m
         JOIN prodotti p ON p.nome='Pasta' AND m.nome='Magazzino Centrale';

INSERT INTO giacenza (id, magazzino_id, prodotto_id, quantita)
SELECT gen_random_uuid(), m.id, p.id, 50
FROM magazzino m
         JOIN prodotti p ON p.nome='Maglietta' AND m.nome='Magazzino Sud';

-- Inserimento utente Admin password:admin
INSERT INTO users (id, username, password, role) VALUES
    (gen_random_uuid(), 'admin', '$2a$10$gZHoqg9WEZLBfsqKEVlCHugFqwGgzf31.F6zLf.EsrpkA8bYqtatC', 'ROLE_ADMIN');

-- Inserimento utente Test password:test
INSERT INTO users (id, username, password, role) VALUES
    (gen_random_uuid(), 'test', '$2a$10$iFiGFUwnGpe8dQ.yJtqbaOpZ1wZVCcx3VBWx1A0ITM5xKxN2TqF0K', 'ROLE_VOLONTARIO');

-- Associazione Test al Magazzino Centrale
INSERT INTO user_magazzino (magazzino_id, user_id)
SELECT m.id, u.id
FROM magazzino m
         JOIN users u ON u.username='test'
WHERE m.nome='Magazzino Centrale';

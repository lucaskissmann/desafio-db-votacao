-- Remove os dados das tabelas na ordem reversa das dependências
DELETE FROM assembleias_pautas;
DELETE FROM assembleias;
DELETE FROM pautas_votacoes;
DELETE FROM votos;
DELETE FROM associados;
DELETE FROM pautas;

-- Reinicia os valores da coluna de ID nas tabelas na ordem reversa das dependências
ALTER TABLE assembleias ALTER COLUMN id RESTART WITH 1;
ALTER TABLE votos ALTER COLUMN id RESTART WITH 1;
ALTER TABLE associados ALTER COLUMN id RESTART WITH 1;
ALTER TABLE pautas ALTER COLUMN id RESTART WITH 1;

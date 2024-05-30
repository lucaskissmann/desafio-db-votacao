INSERT INTO votos ( id, voto, ref_associado, ref_pauta ) 
VALUES (
    2,
    1, --Não
    1,
    1
);

INSERT INTO pautas_votacoes ( ref_pauta, ref_votos )
VALUES (
    1,
    2
);
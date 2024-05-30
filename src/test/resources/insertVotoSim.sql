INSERT INTO votos ( id, voto, ref_associado, ref_pauta ) 
VALUES (
    1,
    0, --Sim
    1,
    1
);

INSERT INTO pautas_votacoes ( ref_pauta, ref_votos )
VALUES (
    1,
    1
);
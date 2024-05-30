INSERT INTO votos ( id, voto, ref_associado, ref_pauta ) 
VALUES (
    3,
    2, --Abstenção
    1,
    1
);

INSERT INTO pautas_votacoes ( ref_pauta, ref_votos )
VALUES (
    1,
    3
);
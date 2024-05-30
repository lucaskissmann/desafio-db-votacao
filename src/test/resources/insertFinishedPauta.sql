DELETE FROM pautas;

INSERT INTO pautas ( id, name, description, start_date, end_date )
VALUES (
    1,
    'Pauta teste',
    '',
    DATEADD( 'DAY', -1, CURRENT_TIMESTAMP() ),
    DATEADD( 'DAY', -1, DATEADD('MINUTE', 1, CURRENT_TIMESTAMP() ))
);
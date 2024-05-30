DELETE FROM pautas;

INSERT INTO pautas ( id, name, description, start_date, end_date )
VALUES (
    1,
    'Pauta teste',
    '',
    CURRENT_TIMESTAMP(),
    DATEADD('MINUTE', 1, CURRENT_TIMESTAMP() )
);
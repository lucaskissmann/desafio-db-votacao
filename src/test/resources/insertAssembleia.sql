DELETE FROM assembleias;

INSERT INTO assembleias ( id, name, description, created_at, start_date, end_date ) 
VALUES (
    1,
    'Assembleia teste', 
    '', 
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    DATEADD(DAY, 1, CURRENT_TIMESTAMP)
);
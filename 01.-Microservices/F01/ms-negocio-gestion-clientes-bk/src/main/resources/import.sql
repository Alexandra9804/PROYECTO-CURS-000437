INSERT INTO PERSONA (
    PERSONA_ID,
    ID_TIPO_PERSONA,
    NOMBRES,
    APELLIDO_PATERNO,
    APELLIDO_MATERNO,
    RAZON_SOCIAL,
    ID_TIPO_DOCUMENTO,
    NUMERO_DOCUMENTO,
    DIRECCION,
    TELEFONO,
    CORREO,
    ESTADO
) VALUES (
             1,
             100,
             'Persona Respaldo 1',
             'Apellido Paterno Respaldo 1',
             'Apellido Materno Respaldo 1',
             NULL,
             200,
             '11111111',
             'Av. Backup 123, Lima',
             '900000001',
             'persona1.backup@respaldo.com',
             '1'
         );

INSERT INTO PERSONA (
    PERSONA_ID,
    ID_TIPO_PERSONA,
    NOMBRES,
    APELLIDO_PATERNO,
    APELLIDO_MATERNO,
    RAZON_SOCIAL,
    ID_TIPO_DOCUMENTO,
    NUMERO_DOCUMENTO,
    DIRECCION,
    TELEFONO,
    CORREO,
    ESTADO
) VALUES (
             2,
             100,
             'Persona Respaldo 2',
             'Apellido Paterno Respaldo 2',
             'Apellido Materno Respaldo 2',
             NULL,
             200,
             '22222222',
             'Calle Contingencia 456, Arequipa',
             '900000002',
             'persona2.backup@respaldo.com',
             '1'
         );


INSERT INTO CLIENTE (
    CLIENTE_ID,
    PERSONA_ID,
    ID_TIPO_CLIENTE,
    ESTADO
) VALUES (
             1,
             1,
             300,
             '1'
         );


INSERT INTO CLIENTE (
    CLIENTE_ID,
    PERSONA_ID,
    ID_TIPO_CLIENTE,
    ESTADO
) VALUES (
             2,
             2,
             301,
             ''
         );

commit;
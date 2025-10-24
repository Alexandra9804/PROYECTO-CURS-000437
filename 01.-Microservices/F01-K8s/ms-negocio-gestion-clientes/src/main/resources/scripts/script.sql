------------------------------------------------------------
-- SECUENCIAS
------------------------------------------------------------
CREATE SEQUENCE SEQ_PERSONA START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE SEQ_CLIENTE START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;


------------------------------------------------------------
-- TABLA: TIPO_PERSONA
------------------------------------------------------------
DROP TABLE TIPO_PERSONA;
CREATE TABLE TIPO_PERSONA (
                              ID_TIPO_PERSONA             NUMBER(10)       PRIMARY KEY,
                              DESCRIPCION                 VARCHAR2(100)    NOT NULL,
                              CODIGO                      VARCHAR2(10)     UNIQUE,
                              AUD_REGISTRO_FECHA          TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
                              AUD_REGISTRO_ID_USUARIO     NUMBER(10)       DEFAULT 1,
                              AUD_ACTUALIZACION_FECHA     TIMESTAMP        NULL,
                              AUD_ACTUALIZACION_ID_USUARIO NUMBER(10)      NULL,
                              ESTADO                      CHAR(1)          DEFAULT '1'
);

COMMENT ON TABLE TIPO_PERSONA IS 'Catálogo de tipos de persona.';

------------------------------------------------------------
-- TABLA: TIPO_DOCUMENTO
------------------------------------------------------------
DROP TABLE TIPO_DOCUMENTO;
CREATE TABLE TIPO_DOCUMENTO (
                                ID_TIPO_DOCUMENTO           NUMBER(10)       PRIMARY KEY,
                                DESCRIPCION                 VARCHAR2(100)    NOT NULL,
                                CODIGO                      VARCHAR2(10)     UNIQUE,
                                AUD_REGISTRO_FECHA          TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
                                AUD_REGISTRO_ID_USUARIO     NUMBER(10)       DEFAULT 1,
                                AUD_ACTUALIZACION_FECHA     TIMESTAMP        NULL,
                                AUD_ACTUALIZACION_ID_USUARIO NUMBER(10)      NULL,
                                ESTADO                      CHAR(1)          DEFAULT '1'
);

COMMENT ON TABLE TIPO_DOCUMENTO IS 'Catálogo de tipos de documento: DNI, RUC, CE, etc.';


------------------------------------------------------------
-- TABLA: TIPO_CLIENTE
------------------------------------------------------------
DROP TABLE TIPO_CLIENTE;
CREATE TABLE TIPO_CLIENTE (
                              ID_TIPO_CLIENTE             NUMBER(10)       PRIMARY KEY,
                              DESCRIPCION                 VARCHAR2(100)    NOT NULL,
                              CODIGO                      VARCHAR2(10)     UNIQUE,
                              AUD_REGISTRO_FECHA          TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
                              AUD_REGISTRO_ID_USUARIO     NUMBER(10)       DEFAULT 1,
                              AUD_ACTUALIZACION_FECHA     TIMESTAMP        NULL,
                              AUD_ACTUALIZACION_ID_USUARIO NUMBER(10)      NULL,
                              ESTADO                      CHAR(1)          DEFAULT '1'
);

COMMENT ON TABLE TIPO_CLIENTE IS 'Catálogo de tipos de cliente: Regular, Corporativo, Premium, etc.';


------------------------------------------------------------
-- TABLA: PERSONA
------------------------------------------------------------
DROP TABLE PERSONA;
CREATE TABLE PERSONA (
                         PERSONA_ID                  NUMBER(10)       PRIMARY KEY,
                         ID_TIPO_PERSONA             NUMBER(10)       NOT NULL,
                         NOMBRES                     VARCHAR2(100),
                         APELLIDO_PATERNO            VARCHAR2(100),
                         APELLIDO_MATERNO            VARCHAR2(100),
                         RAZON_SOCIAL                VARCHAR2(200),
                         ID_TIPO_DOCUMENTO           NUMBER(10)       NOT NULL,
                         NUMERO_DOCUMENTO            VARCHAR2(20)     UNIQUE NOT NULL,
                         DIRECCION                   VARCHAR2(200),
                         TELEFONO                    VARCHAR2(20),
                         CORREO                      VARCHAR2(120),
                         AUD_REGISTRO_FECHA          TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
                         AUD_REGISTRO_ID_USUARIO     NUMBER(10)       DEFAULT 1,
                         AUD_ACTUALIZACION_FECHA     TIMESTAMP        NULL,
                         AUD_ACTUALIZACION_ID_USUARIO NUMBER(10)      NULL,
                         ESTADO                      CHAR(1)          DEFAULT '1',
                         CONSTRAINT FK_PERSONA_TIPO_PERSONA FOREIGN KEY (ID_TIPO_PERSONA)
                             REFERENCES TIPO_PERSONA (ID_TIPO_PERSONA),
                         CONSTRAINT FK_PERSONA_TIPO_DOCUMENTO FOREIGN KEY (ID_TIPO_DOCUMENTO)
                             REFERENCES TIPO_DOCUMENTO (ID_TIPO_DOCUMENTO)
);

COMMENT ON TABLE PERSONA IS 'Tabla que almacena los datos de las personas que son clientes.';
COMMENT ON COLUMN PERSONA.NUMERO_DOCUMENTO IS 'Número único de documento de la persona.';


------------------------------------------------------------
-- TABLA: CLIENTE
------------------------------------------------------------
DROP TABLE CLIENTE;
CREATE TABLE CLIENTE (
                         CLIENTE_ID                  NUMBER(10)       PRIMARY KEY,
                         PERSONA_ID                  NUMBER(10)       NOT NULL,
                         ID_TIPO_CLIENTE             NUMBER(10)       NOT NULL,
                         AUD_REGISTRO_FECHA          TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
                         AUD_REGISTRO_ID_USUARIO     NUMBER(10)       DEFAULT 1,
                         AUD_ACTUALIZACION_FECHA     TIMESTAMP        NULL,
                         AUD_ACTUALIZACION_ID_USUARIO NUMBER(10)      NULL,
                         ESTADO                      CHAR(1)          DEFAULT '1',
                         CONSTRAINT FK_CLIENTE_PERSONA FOREIGN KEY (PERSONA_ID)
                             REFERENCES PERSONA (PERSONA_ID),
                         CONSTRAINT FK_CLIENTE_TIPO_CLIENTE FOREIGN KEY (ID_TIPO_CLIENTE)
                             REFERENCES TIPO_CLIENTE (ID_TIPO_CLIENTE)
);

COMMENT ON TABLE CLIENTE IS 'Tabla que almacena los clientes registrados.';
COMMENT ON COLUMN CLIENTE.PERSONA_ID IS 'Referencia a la persona asociada.';
COMMENT ON COLUMN CLIENTE.ID_TIPO_CLIENTE IS 'Referencia al tipo de cliente.';


INSERT INTO TIPO_PERSONA (ID_TIPO_PERSONA, DESCRIPCION, CODIGO) VALUES (100, 'Natural', 'NAT');
INSERT INTO TIPO_PERSONA (ID_TIPO_PERSONA, DESCRIPCION, CODIGO) VALUES (101, 'Jurídica', 'JUR');

INSERT INTO TIPO_DOCUMENTO (ID_TIPO_DOCUMENTO, DESCRIPCION, CODIGO) VALUES (200, 'DNI', 'DNI');
INSERT INTO TIPO_DOCUMENTO (ID_TIPO_DOCUMENTO, DESCRIPCION, CODIGO) VALUES (201, 'RUC', 'RUC');
INSERT INTO TIPO_DOCUMENTO (ID_TIPO_DOCUMENTO, DESCRIPCION, CODIGO) VALUES (202, 'Carné de Extranjería', 'CE');

INSERT INTO TIPO_CLIENTE (ID_TIPO_CLIENTE, DESCRIPCION, CODIGO) VALUES (300, 'Regular', 'REG');
INSERT INTO TIPO_CLIENTE (ID_TIPO_CLIENTE, DESCRIPCION, CODIGO) VALUES (301, 'Corporativo', 'CORP');
INSERT INTO TIPO_CLIENTE (ID_TIPO_CLIENTE, DESCRIPCION, CODIGO) VALUES (302, 'Premium', 'PREM');

INSERT INTO PERSONA (
    PERSONA_ID,
    ID_TIPO_PERSONA,
    NOMBRES,
    APELLIDO_PATERNO,
    APELLIDO_MATERNO,
    ID_TIPO_DOCUMENTO,
    NUMERO_DOCUMENTO,
    DIRECCION,
    TELEFONO,
    CORREO
) VALUES (
             1,
             100, -- Natural
             'Luis Alberto',
             'Hernandez',
             'Soto',
             200, -- DNI
             '77586904',
             'Av. Los Robles 123, Lima',
             '999888777',
             'lhernandez@example.com'
         );

INSERT INTO PERSONA (
    PERSONA_ID,
    ID_TIPO_PERSONA,
    NOMBRES,
    APELLIDO_PATERNO,
    APELLIDO_MATERNO,
    ID_TIPO_DOCUMENTO,
    NUMERO_DOCUMENTO,
    DIRECCION,
    TELEFONO,
    CORREO
) VALUES (
             2,
             100, -- Natural
             'Maria Fernanda',
             'Rojas',
             'Perez',
             200, -- DNI
             '44556677',
             'Jr. Primavera 432, Arequipa',
             '988777666',
             'mrojas@example.com'
         );

------------------------------------------------------------
-- DATOS DE CLIENTES
------------------------------------------------------------
INSERT INTO CLIENTE (
    CLIENTE_ID,
    PERSONA_ID,
    ID_TIPO_CLIENTE
) VALUES (
             SEQ_CLIENTE.NEXTVAL,
             1, -- Luis Alberto
             300 -- Regular
         );

INSERT INTO CLIENTE (
    CLIENTE_ID,
    PERSONA_ID,
    ID_TIPO_CLIENTE
) VALUES (
             SEQ_CLIENTE.NEXTVAL,
             2, -- Maria Fernanda
             302 -- Premium
         );

COMMIT;

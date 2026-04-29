CREATE TABLE alquiler_estado (
id_alquiler_estado SERIAL PRIMARY KEY,
descripcion VARCHAR(100) NOT NULL,
activo BOOLEAN DEFAULT TRUE,
aud_registro_fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
aud_registro_id_usuario BIGINT,
aud_actualizacion_fecha TIMESTAMP,
aud_actualizacion_id_usuario BIGINT,
estado CHAR(1) DEFAULT '1'
);

INSERT INTO alquiler_estado (descripcion)
VALUES
    ('PENDIENTE_VALIDACION'),
    ('CONFIRMADO'),
    ('RECHAZADO'),
    ('EN_CURSO'),
    ('FINALIZADO'),
    ('ANULADO');

CREATE TABLE alquiler (
id_alquiler SERIAL PRIMARY KEY,
id_cliente BIGINT NOT NULL,
fecha_inicio DATE NOT NULL,
fecha_fin DATE NOT NULL,
sub_total DOUBLE PRECISION,
igv DOUBLE PRECISION,
total DOUBLE PRECISION,
id_alquiler_estado BIGINT NOT NULL REFERENCES alquiler_estado(id_alquiler_estado),
aud_registro_fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
aud_registro_id_usuario BIGINT,
aud_actualizacion_fecha TIMESTAMP,
aud_actualizacion_id_usuario BIGINT,
estado VARCHAR(1) DEFAULT '1',
fecha_registro DATE,
id_vehiculo BIGINT
);


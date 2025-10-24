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

INSERT INTO alquiler_estado (descripcion) VALUES ('Pendiente de pago'), ('Pagado'), ('En proceso'), ('Anulado');

CREATE TABLE alquiler_cabecera (
id_alquiler SERIAL PRIMARY KEY,
id_cliente BIGINT NOT NULL,
fecha_inicio DATE NOT NULL,
fecha_fin DATE NOT NULL,
sub_total NUMERIC(12,2),
igv NUMERIC(12,2),
total NUMERIC(12,2),
id_alquiler_estado INT NOT NULL REFERENCES alquiler_estado(id_alquiler_estado),
aud_registro_fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
aud_registro_id_usuario BIGINT,
aud_actualizacion_fecha TIMESTAMP,
aud_actualizacion_id_usuario BIGINT,
estado CHAR(1) DEFAULT '1'
);

CREATE TABLE alquiler_detalle (
id_alquiler_detalle SERIAL PRIMARY KEY,
id_alquiler BIGINT NOT NULL REFERENCES alquiler_cabecera(id_alquiler),
id_vehiculo BIGINT NOT NULL,
precio_unitario NUMERIC(12,2) NOT NULL,
cantidad INT NOT NULL,
sub_total NUMERIC(12,2),
igv NUMERIC(12,2),
total NUMERIC(12,2),
aud_registro_fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
aud_registro_id_usuario BIGINT,
aud_actualizacion_fecha TIMESTAMP,
aud_actualizacion_id_usuario BIGINT,
estado CHAR(1) DEFAULT '1'
);
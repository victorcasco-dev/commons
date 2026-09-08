-- liquibase formatted sql

-- changeset victor:002-create-catalogos
-- comment: Catálogos reutilizables (líneas de investigación y tipos de consulta)

CREATE TABLE linea_investigacion (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(500),
    activa BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_linea_investigacion_nombre UNIQUE (nombre)
);

CREATE TABLE tipo_consulta (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(500),
    activa BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_tipo_consulta_nombre UNIQUE (nombre)
);

CREATE TABLE configuracion (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    clave VARCHAR(100) NOT NULL,
    valor VARCHAR(500) NOT NULL,
    descripcion VARCHAR(255),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_configuracion_clave UNIQUE (clave)
);

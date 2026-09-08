-- liquibase formatted sql

-- changeset victor:001-create-usuario
-- comment: Jerarquía JOINED de usuarios (alumno, profesor, administrador)

CREATE TABLE usuario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    tipo_usuario VARCHAR(32) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_usuario_email UNIQUE (email),
    CONSTRAINT chk_usuario_tipo CHECK (tipo_usuario IN ('ALUMNO', 'PROFESOR', 'ADMINISTRADOR'))
);

CREATE TABLE profesor (
    id UUID PRIMARY KEY REFERENCES usuario (id) ON DELETE CASCADE,
    titulo_academico VARCHAR(150)
);

CREATE TABLE administrador (
    id UUID PRIMARY KEY REFERENCES usuario (id) ON DELETE CASCADE,
    cargo VARCHAR(150)
);

CREATE TABLE alumno (
    id UUID PRIMARY KEY REFERENCES usuario (id) ON DELETE CASCADE,
    matricula VARCHAR(32) NOT NULL,
    id_profesor_tutor UUID REFERENCES profesor (id) ON DELETE SET NULL,
    CONSTRAINT uk_alumno_matricula UNIQUE (matricula)
);

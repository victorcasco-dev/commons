-- liquibase formatted sql

-- changeset victor:004-create-franja-horaria
-- comment: Disponibilidad horaria del profesor. Una reserva activa por franja.

CREATE TABLE franja_horaria (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_profesor UUID NOT NULL REFERENCES profesor (id) ON DELETE CASCADE,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    estado VARCHAR(32) NOT NULL DEFAULT 'DISPONIBLE',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_franja_profesor_fecha_inicio UNIQUE (id_profesor, fecha, hora_inicio),
    CONSTRAINT chk_franja_horario CHECK (hora_fin > hora_inicio),
    CONSTRAINT chk_franja_estado CHECK (estado IN ('DISPONIBLE', 'OCUPADA', 'CANCELADA'))
);

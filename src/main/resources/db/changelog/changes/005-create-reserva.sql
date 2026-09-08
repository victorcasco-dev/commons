-- liquibase formatted sql

-- changeset rodrigo:005-create-reserva
-- comment: Reserva (header) <-> TipoConsulta (catálogo) vía detalle_reserva

CREATE TABLE reserva (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_alumno UUID NOT NULL REFERENCES alumno (id) ON DELETE RESTRICT,
    id_franja UUID NOT NULL REFERENCES franja_horaria (id) ON DELETE RESTRICT,
    estado VARCHAR(32) NOT NULL DEFAULT 'PENDIENTE',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_reserva_estado CHECK (estado IN ('PENDIENTE', 'CONFIRMADA', 'CANCELADA', 'COMPLETADA'))
);

CREATE UNIQUE INDEX uk_reserva_franja_activa
    ON reserva (id_franja)
    WHERE estado IN ('PENDIENTE', 'CONFIRMADA');

CREATE TABLE detalle_reserva (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_reserva UUID NOT NULL REFERENCES reserva (id) ON DELETE CASCADE,
    id_tipo_consulta UUID NOT NULL REFERENCES tipo_consulta (id) ON DELETE RESTRICT,
    comentario VARCHAR(500),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_detalle_reserva UNIQUE (id_reserva, id_tipo_consulta)
);

-- liquibase formatted sql

-- changeset victor:003-create-profesor-linea
-- comment: Tabla puente Profesor (header) <-> LineaInvestigacion (catálogo)

CREATE TABLE profesor_linea (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_profesor UUID NOT NULL REFERENCES profesor (id) ON DELETE CASCADE,
    id_linea_investigacion UUID NOT NULL REFERENCES linea_investigacion (id) ON DELETE RESTRICT,
    nivel_experiencia VARCHAR(32) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_profesor_linea UNIQUE (id_profesor, id_linea_investigacion),
    CONSTRAINT chk_nivel_experiencia CHECK (nivel_experiencia IN ('BASICO', 'MEDIO', 'EXPERTO'))
);

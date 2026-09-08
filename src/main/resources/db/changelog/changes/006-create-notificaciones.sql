-- liquibase formatted sql

-- changeset rodrigo:006-create-notificaciones
-- comment: Plantillas de mail, formulario de contacto y cola de notificaciones

CREATE TABLE plantilla_mail (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(80) NOT NULL,
    asunto VARCHAR(255) NOT NULL,
    cuerpo_html TEXT NOT NULL,
    activa BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_plantilla_mail_codigo UNIQUE (codigo)
);

CREATE TABLE consulta_contacto (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    mensaje VARCHAR(2000) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE notificacion (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_usuario UUID NOT NULL REFERENCES usuario (id) ON DELETE CASCADE,
    tipo VARCHAR(32) NOT NULL,
    canal VARCHAR(32) NOT NULL DEFAULT 'EMAIL',
    estado VARCHAR(32) NOT NULL DEFAULT 'PENDIENTE',
    payload JSONB,
    enviada_at TIMESTAMPTZ,
    error_mensaje VARCHAR(500),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_notificacion_tipo CHECK (tipo IN ('CONFIRMACION_RESERVA', 'RECORDATORIO', 'CANCELACION', 'CONTACTO')),
    CONSTRAINT chk_notificacion_canal CHECK (canal IN ('EMAIL')),
    CONSTRAINT chk_notificacion_estado CHECK (estado IN ('PENDIENTE', 'ENVIADA', 'ERROR'))
);

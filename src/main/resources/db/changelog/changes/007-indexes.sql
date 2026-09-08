-- liquibase formatted sql

-- changeset shared:007-indexes
-- comment: Índices para búsquedas de disponibilidad, reservas y reportes

CREATE INDEX idx_franja_profesor_fecha_estado
    ON franja_horaria (id_profesor, fecha, estado);

CREATE INDEX idx_reserva_alumno_estado
    ON reserva (id_alumno, estado);

CREATE INDEX idx_profesor_linea_linea
    ON profesor_linea (id_linea_investigacion);

CREATE INDEX idx_notificacion_usuario_estado
    ON notificacion (id_usuario, estado);

CREATE INDEX idx_consulta_contacto_email
    ON consulta_contacto (email);

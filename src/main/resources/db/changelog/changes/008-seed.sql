-- liquibase formatted sql

-- changeset shared:008-seed
-- comment: Datos iniciales de catálogos, configuración y plantillas de mail

INSERT INTO linea_investigacion (nombre, descripcion, activa)
VALUES
    ('Inteligencia Artificial', 'Aprendizaje automático, visión y PLN', TRUE),
    ('Redes', 'Redes de computadoras y comunicaciones', TRUE),
    ('Bases de Datos', 'Modelado, SQL y sistemas de datos', TRUE),
    ('Ingeniería de Software', 'Arquitectura, calidad y procesos', TRUE),
    ('Seguridad Informática', 'Criptografía, aplicaciones y redes seguras', TRUE);

INSERT INTO tipo_consulta (nombre, descripcion, activa)
VALUES
    ('Revisión de metodología', 'Revisión del enfoque metodológico de la tesis', TRUE),
    ('Corrección de capítulo', 'Lectura y corrección de un capítulo', TRUE),
    ('Defensa preliminar', 'Simulacro o preparación de defensa', TRUE),
    ('Avance general', 'Seguimiento general del progreso', TRUE);

INSERT INTO configuracion (clave, valor, descripcion)
VALUES
    ('duracion_reunion_minutos', '60', 'Duración estándar de una tutoría'),
    ('anticipacion_minima_horas', '24', 'Horas mínimas de anticipación para reservar');

INSERT INTO plantilla_mail (codigo, asunto, cuerpo_html, activa)
VALUES
    ('CONFIRMACION_RESERVA', 'Confirmación de tutoría', '<p>Tu reserva de tutoría fue confirmada.</p>', TRUE),
    ('RECORDATORIO', 'Recordatorio de tutoría', '<p>Recordatorio: tenés una tutoría próxima.</p>', TRUE),
    ('CANCELACION', 'Cancelación de tutoría', '<p>La reserva de tutoría fue cancelada.</p>', TRUE);

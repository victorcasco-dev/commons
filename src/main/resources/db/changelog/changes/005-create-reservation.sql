-- liquibase formatted sql

-- changeset rodrigo:005-create-reservation
-- comment: Reservation (header) <-> ConsultationType (catalog) via reservation_detail

CREATE TABLE reservation (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL REFERENCES student (id) ON DELETE RESTRICT,
    time_slot_id UUID NOT NULL REFERENCES time_slot (id) ON DELETE RESTRICT,
    status VARCHAR(32) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_reservation_status CHECK (status IN ('PENDING', 'CONFIRMED', 'CANCELLED', 'COMPLETED'))
);

CREATE UNIQUE INDEX uk_reservation_time_slot_active
    ON reservation (time_slot_id)
    WHERE status IN ('PENDING', 'CONFIRMED');

CREATE TABLE reservation_detail (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reservation_id UUID NOT NULL REFERENCES reservation (id) ON DELETE CASCADE,
    consultation_type_id UUID NOT NULL REFERENCES consultation_type (id) ON DELETE RESTRICT,
    comment VARCHAR(500),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_reservation_detail UNIQUE (reservation_id, consultation_type_id)
);

-- liquibase formatted sql

-- changeset victor:004-create-time-slot
-- comment: Professor availability. One active reservation per time slot.

CREATE TABLE time_slot (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    professor_id UUID NOT NULL REFERENCES professor (id) ON DELETE CASCADE,
    slot_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    status VARCHAR(32) NOT NULL DEFAULT 'AVAILABLE',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_time_slot_professor_date_start UNIQUE (professor_id, slot_date, start_time),
    CONSTRAINT chk_time_slot_range CHECK (end_time > start_time),
    CONSTRAINT chk_time_slot_status CHECK (status IN ('AVAILABLE', 'BOOKED', 'CANCELLED'))
);

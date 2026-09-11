-- liquibase formatted sql

-- changeset victor:001-create-users
-- comment: JOINED user hierarchy (student, professor, administrator)

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    user_type VARCHAR(32) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_users_email UNIQUE (email),
    CONSTRAINT chk_users_type CHECK (user_type IN ('STUDENT', 'PROFESSOR', 'ADMINISTRATOR'))
);

CREATE TABLE professor (
    id UUID PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    academic_title VARCHAR(150)
);

CREATE TABLE administrator (
    id UUID PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    position VARCHAR(150)
);

CREATE TABLE student (
    id UUID PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    student_code VARCHAR(32) NOT NULL,
    tutor_professor_id UUID REFERENCES professor (id) ON DELETE SET NULL,
    CONSTRAINT uk_student_code UNIQUE (student_code)
);

-- liquibase formatted sql

-- changeset victor:003-create-professor-research-line
-- comment: Bridge table Professor (header) <-> ResearchLine (catalog)

CREATE TABLE professor_research_line (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    professor_id UUID NOT NULL REFERENCES professor (id) ON DELETE CASCADE,
    research_line_id UUID NOT NULL REFERENCES research_line (id) ON DELETE RESTRICT,
    experience_level VARCHAR(32) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_professor_research_line UNIQUE (professor_id, research_line_id),
    CONSTRAINT chk_experience_level CHECK (experience_level IN ('BASIC', 'INTERMEDIATE', 'EXPERT'))
);

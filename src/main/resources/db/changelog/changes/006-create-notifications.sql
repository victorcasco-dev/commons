-- liquibase formatted sql

-- changeset rodrigo:006-create-notifications
-- comment: Email templates, contact form and notification queue

CREATE TABLE email_template (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(80) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    html_body TEXT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_email_template_code UNIQUE (code)
);

CREATE TABLE contact_inquiry (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    message VARCHAR(2000) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE notification (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    type VARCHAR(32) NOT NULL,
    channel VARCHAR(32) NOT NULL DEFAULT 'EMAIL',
    status VARCHAR(32) NOT NULL DEFAULT 'PENDING',
    payload JSONB,
    sent_at TIMESTAMPTZ,
    error_message VARCHAR(500),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_notification_type CHECK (type IN ('RESERVATION_CONFIRMATION', 'REMINDER', 'CANCELLATION', 'CONTACT')),
    CONSTRAINT chk_notification_channel CHECK (channel IN ('EMAIL')),
    CONSTRAINT chk_notification_status CHECK (status IN ('PENDING', 'SENT', 'ERROR'))
);

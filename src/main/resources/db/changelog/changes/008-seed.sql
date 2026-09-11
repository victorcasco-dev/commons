-- liquibase formatted sql

-- changeset shared:008-seed
-- comment: Initial catalog, settings and email template data

INSERT INTO research_line (name, description, active)
VALUES
    ('Artificial Intelligence', 'Machine learning, computer vision and NLP', TRUE),
    ('Networks', 'Computer networks and communications', TRUE),
    ('Databases', 'Data modeling, SQL and data systems', TRUE),
    ('Software Engineering', 'Architecture, quality and processes', TRUE),
    ('Information Security', 'Cryptography, applications and secure networks', TRUE);

INSERT INTO consultation_type (name, description, active)
VALUES
    ('Methodology review', 'Review of the thesis methodological approach', TRUE),
    ('Chapter correction', 'Reading and correction of a chapter', TRUE),
    ('Preliminary defense', 'Defense rehearsal or preparation', TRUE),
    ('General progress', 'Overall progress follow-up', TRUE);

INSERT INTO setting (setting_key, value, description)
VALUES
    ('meeting_duration_minutes', '60', 'Default tutoring meeting duration'),
    ('min_advance_hours', '24', 'Minimum hours in advance to book a slot');

INSERT INTO email_template (code, subject, html_body, active)
VALUES
    ('RESERVATION_CONFIRMATION', 'Tutoring reservation confirmed', '<p>Your tutoring reservation was confirmed.</p>', TRUE),
    ('REMINDER', 'Tutoring reminder', '<p>Reminder: you have an upcoming tutoring session.</p>', TRUE),
    ('CANCELLATION', 'Tutoring cancellation', '<p>The tutoring reservation was cancelled.</p>', TRUE);

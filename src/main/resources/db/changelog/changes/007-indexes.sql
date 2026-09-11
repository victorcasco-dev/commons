-- liquibase formatted sql

-- changeset shared:007-indexes
-- comment: Indexes for availability, reservations and reports

CREATE INDEX idx_time_slot_professor_date_status
    ON time_slot (professor_id, slot_date, status);

CREATE INDEX idx_reservation_student_status
    ON reservation (student_id, status);

CREATE INDEX idx_professor_research_line_line
    ON professor_research_line (research_line_id);

CREATE INDEX idx_notification_user_status
    ON notification (user_id, status);

CREATE INDEX idx_contact_inquiry_email
    ON contact_inquiry (email);

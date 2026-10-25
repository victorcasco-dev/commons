-- liquibase formatted sql

-- changeset security:009-create-user-role
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public' AND table_name = 'user_role'
-- comment: Multiple roles per user. ms-users also applies db/user-role.sql at startup.

CREATE TABLE user_role (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    role_name VARCHAR(32) NOT NULL,
    CONSTRAINT uk_user_role_user_role UNIQUE (user_id, role_name),
    CONSTRAINT chk_user_role CHECK (role_name IN ('STUDENT', 'PROFESSOR', 'ADMINISTRATOR'))
);

INSERT INTO user_role (user_id, role_name)
SELECT u.id, u.user_type
FROM users u
WHERE NOT EXISTS (
    SELECT 1 FROM user_role r WHERE r.user_id = u.id
);

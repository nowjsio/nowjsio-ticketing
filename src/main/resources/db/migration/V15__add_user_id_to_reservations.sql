ALTER TABLE reservations
    ADD COLUMN user_id BIGINT NOT NULL AFTER id,
    ADD CONSTRAINT fk_reservations_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

CREATE INDEX idx_reservations_user_id
    ON reservations (user_id);

ALTER TABLE payments
    ADD COLUMN user_id BIGINT NOT NULL AFTER id,
    ADD CONSTRAINT fk_payments_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

CREATE INDEX idx_payments_user_id
    ON payments (user_id);

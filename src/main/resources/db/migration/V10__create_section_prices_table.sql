CREATE TABLE IF NOT EXISTS section_prices
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id   BIGINT         NOT NULL,
    seat_id    BIGINT         NOT NULL,
    section_id BIGINT         NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    created_at DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_sp_event (event_id),
    INDEX idx_sp_seat (seat_id),
    INDEX idx_sp_section (section_id),
    CONSTRAINT fk_sp_event
        FOREIGN KEY (event_id)
            REFERENCES events (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_sp_seat
        FOREIGN KEY (seat_id)
            REFERENCES seats (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_sp_section
        FOREIGN KEY (section_id)
            REFERENCES sections (id)
            ON DELETE CASCADE
)

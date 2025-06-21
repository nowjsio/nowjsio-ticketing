CREATE TABLE IF NOT EXISTS tickets
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    schedule_id      BIGINT         NOT NULL,
    seat_id          BIGINT         NOT NULL,
    section_price_id BIGINT         NOT NULL,
    price            DECIMAL(10, 2) NOT NULL,
    status           VARCHAR(255)   NOT NULL,
    created_at       DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_ticket_sched (schedule_id),
    INDEX idx_ticket_seat (seat_id),
    INDEX idx_ticket_sp (section_price_id),
    CONSTRAINT fk_ticket_sched
        FOREIGN KEY (schedule_id)
            REFERENCES event_schedules (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_ticket_seat
        FOREIGN KEY (seat_id)
            REFERENCES seats (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_ticket_sp
        FOREIGN KEY (section_price_id)
            REFERENCES section_prices (id)
            ON DELETE CASCADE
)

CREATE TABLE IF NOT EXISTS event_schedules
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id   BIGINT   NOT NULL,
    starts_at  DATETIME NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_sched_event (event_id),
    CONSTRAINT fk_sched_event
        FOREIGN KEY (event_id)
            REFERENCES events (id)
            ON DELETE CASCADE
)

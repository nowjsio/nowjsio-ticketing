CREATE TABLE IF NOT EXISTS reservations
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_id   BIGINT       NOT NULL,
    reserved_at DATETIME     NOT NULL,
    status      VARCHAR(255) NOT NULL,
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_res_ticket (ticket_id),
    CONSTRAINT fk_res_ticket
        FOREIGN KEY (ticket_id)
            REFERENCES tickets (id)
            ON DELETE CASCADE
)

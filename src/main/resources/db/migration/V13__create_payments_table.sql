CREATE TABLE IF NOT EXISTS payments
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservation_id BIGINT         NOT NULL,
    amount         DECIMAL(10, 2) NOT NULL,
    status         VARCHAR(255)   NOT NULL,
    paid_at        DATETIME       NOT NULL,
    created_at     DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_pay_res (reservation_id),
    CONSTRAINT fk_pay_res
        FOREIGN KEY (reservation_id)
            REFERENCES reservations (id)
            ON DELETE CASCADE
)

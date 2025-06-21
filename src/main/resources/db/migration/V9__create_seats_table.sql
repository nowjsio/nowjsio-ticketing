CREATE TABLE IF NOT EXISTS seats
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    section_id BIGINT     NOT NULL,
    row_label  VARCHAR(5) NOT NULL,
    num        INT        NOT NULL,
    created_at DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_seat_section (section_id),
    CONSTRAINT fk_seat_section
        FOREIGN KEY (section_id)
            REFERENCES sections (id)
            ON DELETE CASCADE
)

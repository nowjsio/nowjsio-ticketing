CREATE TABLE audit_logs
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    action        VARCHAR(20)  NOT NULL,
    entity_name   VARCHAR(100) NOT NULL,
    entity_id     VARCHAR(50),
    previous_data TEXT,
    new_data      TEXT,
    user_name     VARCHAR(255),
    ip_address    VARCHAR(45),
    user_agent    VARCHAR(255),
    description   VARCHAR(255),
    created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
